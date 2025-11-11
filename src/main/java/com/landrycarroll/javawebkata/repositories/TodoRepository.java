package com.landrycarroll.javawebkata.repositories;

import com.landrycarroll.javawebkata.entities.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoRepository {
    Todo addTodo(Todo todo);
    Todo updateTodo(Todo todo);
    UUID deleteTodo(UUID todoId);
    List<Todo> viewTodos();
    Todo getTodo(UUID todoId);
}
