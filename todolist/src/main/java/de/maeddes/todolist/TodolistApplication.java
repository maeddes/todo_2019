package de.maeddes.todolist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodolistApplication {

	@Autowired
	TodoRepository todoRepository;

	@GetMapping("/todos/")
	List<String> getTodos() {

		List<String> todos = new ArrayList<String>();

		// for(Todo todo : todoRepository.findAll()) todos.add(todo.getTodo());
		todoRepository.findAll().forEach(todo -> todos.add(todo.getTodo()));

		return todos;
	}

	@PostMapping("/todos/{todo}")
	String addTodo(@PathVariable String todo) {

		todoRepository.save(new Todo(todo));
		return "added " + todo;
	}

	@DeleteMapping("/todos/{todo}")
	String removeTodo(@PathVariable String todo) {

		todoRepository.deleteById(todo);
		return "removed " + todo;

	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}

@Entity
class Todo{

	@Id
	String todo;

	public Todo() {
	}

	public Todo(String todo) {
		this.todo = todo;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

}

@RepositoryRestResource(path = "todo-hal")
interface TodoRepository extends CrudRepository<Todo, String> {

}