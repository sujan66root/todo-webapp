package com.sujan.springboot.todowebapp.todo;

import com.sujan.springboot.todowebapp.login.AuthenticateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("todos")
public class TodoController {

    private final TodoService todoService;
    private final AuthenticateService authenticateService;

    public TodoController(TodoService todoService, AuthenticateService authenticateService) {
        this.todoService = todoService;
        this.authenticateService = authenticateService;
    }

    // List all todos for the logged-in user
    @RequestMapping(method = RequestMethod.GET)
    public String listAllTodos(ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Redirect to login if no username found in session
        }
        List<Todo> todos = todoService.getTodoByUsername(username);
        model.addAttribute("todos", todos);
        return "todos";
    }

    // Show form to add a new todo
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addTodoShowForm(ModelMap model) {
        model.addAttribute("todo", new Todo());
        return "todo-form";
    }

    // Save the todo form
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addTodoSaveForm(@ModelAttribute("todo") Todo todo, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Redirect to login if no username found in session
        }
        todo.setUsername(username);
        todoService.addTodo(todo.getUsername(), todo.getDescription(), todo.getTargetDate(), todo.isStatus());
        return "redirect:/todos";
    }

    // Show form to edit the todo
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateTodoShowForm(@PathVariable("id") long id, ModelMap model) {
        Optional<Todo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            model.addAttribute("todo", todo.get());
            return "todo-form";
        } else {
            return "redirect:/todos";
        }
    }

    // Edit the todo form handle submission
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateTodo(@ModelAttribute("todo") Todo todo, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Redirect to login if no username found in session
        }
        todo.setUsername(username);
        todoService.saveUpdateTodo(todo);
        return "redirect:/todos";
    }

    // Delete the todo through id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTodoById(@PathVariable("id") long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }
}
