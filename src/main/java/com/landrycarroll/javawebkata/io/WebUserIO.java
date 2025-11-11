package com.landrycarroll.javawebkata.io;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebUserIO implements UserIO {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public WebUserIO(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String readInput(String paramName) {
        return request.getParameter(paramName); // read from form fields
    }

    @Override
    public void writeOutput(String output) {
        try {
            response.getWriter().println(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendError(String errorMessage) {
        request.setAttribute("error", errorMessage);
    }
}
