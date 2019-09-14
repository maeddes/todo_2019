package de.maeddes.todolist;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SpringBootApplication
public class TodolistApplication {

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

@RepositoryRestResource(path = "todos")
interface TodoRepository extends CrudRepository<Todo, String> {

}