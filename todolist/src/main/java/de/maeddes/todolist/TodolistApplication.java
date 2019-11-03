package de.maeddes.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
public class TodolistApplication {

	List<String> todos = new ArrayList<String>();

	@GetMapping("/todos/")
	List<String> getTodos() {

		return todos;
	}

	@PostMapping("/todos/{todo}")
	String addTodo(@PathVariable String todo) {

		todos.add(todo);
		return "added " + todo;
	}

	@DeleteMapping("/todos/{todo}")
	String removeTodo(@PathVariable String todo) {

		todos.remove(todo);
		return "removed " + todo;

	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
