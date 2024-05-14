package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Todo;
import com.example.demo.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping
    public List<Todo> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Integer id){

        Optional<Todo> optionalTodo = service.findById(id);
        if(optionalTodo.isPresent()){
            return ResponseEntity.ok(optionalTodo.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(todo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Integer id, @RequestBody Todo todo){
        Optional<Todo> optionalTodo = service.findById(id);
        if(optionalTodo.isPresent()){
            if (todo.getDescription()!=null){
                optionalTodo.get().setDescription(todo.getDescription());
            }
            if (todo.getPriority()!=null){
                optionalTodo.get().setPriority(todo.getPriority());
            }
            if (todo.getStatus()!=null){
                optionalTodo.get().setStatus(todo.getStatus());
            }
            return ResponseEntity.status(HttpStatus.OK).body(service.save(optionalTodo.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Todo todo = new Todo();
        todo.setId(id);
        Optional<Todo> optionalTodo = service.delete(todo);
        if(optionalTodo.isPresent()){
            return ResponseEntity.ok(optionalTodo.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }


}
