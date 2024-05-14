package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Todo;

public interface TodoService {

    List<Todo> findAll();
    Optional<Todo> findById(Integer id);
    Todo save(Todo todo);
    Optional<Todo> delete(Todo todo);

}
