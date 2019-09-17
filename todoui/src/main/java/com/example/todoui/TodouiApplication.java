package com.example.todoui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Controller
public class TodouiApplication {

	@Value("${backend.host:localhost}")
	String backendHost;

	@Value("${backend.port:8080}")
	String backendPort;

	String endpoint;

	RestTemplate template = new RestTemplate();

	@PostConstruct
	private void initEndpoint(){

		endpoint = "http://"+backendHost+":"+backendPort;

	}

	@GetMapping
	public String getItems(Model model) {

		System.out.println(" Invoking: " + endpoint + "/todos/");
		ResponseEntity<String[]> response = null;

		try {
			
            response = template.getForEntity(endpoint + "/todos/", String[].class);

		} catch (Exception e) {

			System.out.println(" Backend down" );
			String[] responseValue = new String[] { "Fix your backend"};
			model.addAttribute("items", responseValue);
		}

		if (response != null)
			model.addAttribute("items", response.getBody());

		return "items";

	}

	@PostMapping
	public String addItem(String toDo) {

		try {
			
			template.postForEntity(endpoint + "/todos/" + toDo, null, String.class);
		
		} catch (Exception e) {

			System.out.println(" POST failed ");
			
		}
		return "redirect:/";

	}

	@PostMapping("{toDo}")
	public String setItemDone(@PathVariable String toDo) {

		try {

			template.delete(endpoint + "/todos/" + toDo);

		} catch (Exception e) {

			System.out.println(" DELETE failed ");

		}
		return "redirect:/";

	}

	

	public static void main(String[] args) {
		SpringApplication.run(TodouiApplication.class, args);
	}

}

