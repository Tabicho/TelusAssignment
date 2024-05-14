package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Todo;
import com.example.demo.repositiories.TodoRepository;

@SpringBootTest
@Transactional
@Rollback
class DemoApplicationTests {

	@Autowired
	private TodoRepository todoRepository;

	@Test
	void saveTodoTest() {

		Todo todo = new Todo();
		todo.setDescription("Test");
		todo.setPriority("High");
		todo.setStatus("To do");
		
		todoRepository.save(todo);
		Assertions.assertThat(todo.getId()).isGreaterThan(0);

		Todo findTodo = todoRepository.findById(todo.getId()).orElse(null);
		Assertions.assertThat(findTodo).isNotNull();

	}

	@Test
	void getTodoByIdTest(){
		Todo findTodo = todoRepository.findById(1).orElse(null);
		Assertions.assertThat(findTodo).isNotNull();

	}

	@Test
	void getTodosByIdTest(){
		List<Todo> findTodoList = (List<Todo>)todoRepository.findAll();
		Assertions.assertThat(findTodoList.size()).isNotZero();

	}

	@Test
	void updateTodoByIdTest(){
		Todo findTodo = todoRepository.findById(1).orElse(null);			
		findTodo.setDescription("Test patch");
		todoRepository.save(findTodo);
		Assertions.assertThat(findTodo.getDescription()).isEqualTo("Test patch");

	}

	
	@Test
	void deleteTodoByIdTest(){
		Todo findTodo = todoRepository.findById(1).orElse(null);
		if (findTodo != null){
			todoRepository.delete(findTodo);
			List<Todo> findTodoList = (List<Todo>)todoRepository.findAll();
			Assertions.assertThat(findTodoList.size()).isZero();
		}
		

	}

}
