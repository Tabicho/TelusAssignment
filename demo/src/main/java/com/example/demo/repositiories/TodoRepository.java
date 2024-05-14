package com.example.demo.repositiories;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Todo;
public interface TodoRepository extends CrudRepository<Todo, Integer>{

}
