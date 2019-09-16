package com.example.todolist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.*
import javax.persistence.*


@SpringBootApplication
@RestController
class TodolistApplication(val todoRepository: TodoRepository){

	@PostMapping("/todos/{todo}")
	fun addTodo(@PathVariable todo:String):String {
		todoRepository.save(Todo(todo))
		return "added " + todo
	}

	@GetMapping("/todos/")
	fun getTodos():List<String>{
		var todos = ArrayList<String>()
		todoRepository.findAll().forEach<Todo> { element -> todos.add(element.todo) }
		return todos

	}

	@DeleteMapping("/todos/{todo}")
	fun removeTodo(@PathVariable todo:String):String {
		todoRepository.deleteById(todo)
		return "removed " + todo
	}


}

fun main(args: Array<String>) {
	runApplication<TodolistApplication>(*args)
}

@Entity
data class Todo(@Id var todo: String = "") {
}

@RepositoryRestResource(path = "todo-hal")
interface TodoRepository: CrudRepository<Todo, String>