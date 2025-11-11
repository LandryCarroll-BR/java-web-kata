package com.landrycarroll.javawebkata.controllers;

import com.landrycarroll.javawebkata.entities.Todo;
import com.landrycarroll.javawebkata.exceptions.TodoExceptions;
import com.landrycarroll.javawebkata.io.UserIO;
import com.landrycarroll.javawebkata.repositories.TodoRepository;

import java.util.UUID;

public class TodoController {
    private final UserIO io;
    private final TodoRepository todos;

    public TodoController(UserIO io, TodoRepository todos) {
        this.io = io;
        this.todos = todos;
    }

    public boolean addTodo() {
        try {
            String title = io.readInput("title");
            String description = io.readInput("description");
            Todo newTodo = new Todo(title, description);
            Todo createdTodo = todos.addTodo(newTodo);
            io.writeOutput("Successfully added new todo with ID of: " + newTodo.getId());
            return true;
        } catch (Exception ex) {
            io.sendError("Error: " + ex.getMessage() + ", please try again.");
            return false;
        }
    }

    public boolean deleteTodo() {
        try {
            String todoId = io.readInput("id");
            UUID id = UUID.fromString(todoId);
            UUID deletedId = todos.deleteTodo(id);
            io.writeOutput("Successfully deleted todo with ID of: " + deletedId);
            return true;
        } catch (Exception ex) {
            io.sendError("Error: " + ex.getMessage() + ", please try again.");
            return false;
        }
    }

    public boolean viewTodos() {
        try {
            if (todos.viewTodos().isEmpty()) {
                io.writeOutput("Great news — you have no todos! (Or maybe that's bad news... and you should probably do something with your life).");
                return true;
            }
            io.writeOutput(todos.viewTodos().toString());
            return true;
        } catch (Exception ex) {
            io.sendError("Error: " + ex.getMessage() + ", please try again.");
            return false;
        }
    }

    public boolean toggleTodo() {
        try {
            String todoId = io.readInput("id");
            UUID id = UUID.fromString(todoId);
            Todo existingTodo = todos.getTodo(id);

            if (existingTodo.isPresent()) {
                existingTodo.setCompleted(!existingTodo.isCompleted());
                Todo updatedTodo = todos.updateTodo(existingTodo);
                return true;
            } else {
                throw new TodoExceptions.TodoDoesNotExistException("Todo with id " + todoId + " does not exist.");
            }
        } catch (Exception ex) {
            io.sendError("Error: " + ex.getMessage() + ", please try again.");
            return false;
        }
    }

}
