package com.landrycarroll.javawebkata.servlets;

import com.landrycarroll.javawebkata.controllers.TodoController;
import com.landrycarroll.javawebkata.io.WebUserIO;
import com.landrycarroll.javawebkata.repositories.HibernateTodoRepository;
import com.landrycarroll.javawebkata.repositories.TodoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/todos")
public class ViewTodosServlet extends HttpServlet {
    private TodoRepository todoRepository;

    @Override
    public void init() {
        this.todoRepository = new HibernateTodoRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        WebUserIO io = new WebUserIO(req, resp);
        TodoController controller = new TodoController(io, todoRepository);

        controller.viewTodos();

        req.setAttribute("todos", todoRepository.viewTodos());
        req.getRequestDispatcher("/todos.jsp").forward(req, resp);
    }
}
