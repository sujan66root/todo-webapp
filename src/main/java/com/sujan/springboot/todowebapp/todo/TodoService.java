package com.sujan.springboot.todowebapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;

//    add a new todo
    public void addTodo(String username, String description, LocalDate targetDate, boolean status){
        Todo todo = new Todo(0, username, description, targetDate, status);
        todoRepo.save(todo);
    }

//    retrieve all todos
    public List<Todo> getAllTodos(){
        return todoRepo.findAll();
    }
//    retrieve todos by username
    public List<Todo> getTodoByUsername(String username){
        return todoRepo.findByUsername(username);
    }

//    retrieve single todo by id
    public Optional<Todo> getTodoById(long id){
        return todoRepo.findById(id);
    }

//    save a todo or update it
    public void saveUpdateTodo(Todo todo){
        todoRepo.save(todo);
    }

    public void deleteTodoById(long id){
        todoRepo.deleteById(id);
    }

}
