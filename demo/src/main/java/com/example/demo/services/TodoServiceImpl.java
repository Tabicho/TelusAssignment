package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Todo;
import com.example.demo.repositiories.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository repo;

    @Transactional(readOnly = true)
    @Override
    public List<Todo> findAll() {
        return (List<Todo>)repo.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Todo> findById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public Todo save(Todo todo) {
        return repo.save(todo);
    }

    @Transactional
    @Override
    public Optional<Todo> delete(Todo todo) {
        Optional<Todo> todoOptional = repo.findById(todo.getId());
        todoOptional.ifPresent(todoDb-> {
            repo.delete(todoDb);
        });
        return todoOptional;

        
    }

}
