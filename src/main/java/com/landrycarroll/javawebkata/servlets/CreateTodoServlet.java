package com.landrycarroll.javawebkata.servlets;

import java.io.*;

import com.landrycarroll.javawebkata.controllers.TodoController;
import com.landrycarroll.javawebkata.io.WebUserIO;
import com.landrycarroll.javawebkata.repositories.HibernateTodoRepository;
import com.landrycarroll.javawebkata.repositories.TodoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/todos/create")
public class CreateTodoServlet extends HttpServlet {
    private TodoRepository todoRepository;

    @Override
    public void init() {
        this.todoRepository = new HibernateTodoRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        WebUserIO io = new WebUserIO(req, resp);
        TodoController controller = new TodoController(io, todoRepository);

        boolean success = controller.addTodo();

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/todos");
        } else {
            req.getRequestDispatcher("/create.jsp").forward(req, resp);
        }
    }
}
