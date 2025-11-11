package com.landrycarroll.javawebkata.servlets;

import java.io.*;

import com.landrycarroll.javawebkata.controllers.TodoController;
import com.landrycarroll.javawebkata.io.WebUserIO;
import com.landrycarroll.javawebkata.repositories.HibernateTodoRepository;
import com.landrycarroll.javawebkata.repositories.TodoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/todos/delete")
public class DeleteTodoServlet extends HttpServlet {
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

        controller.deleteTodo();

        resp.sendRedirect(req.getContextPath() + "/todos");
    }

}
