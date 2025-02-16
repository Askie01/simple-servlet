package com.example.servlettutorial.controller;

import com.example.servlettutorial.annotation.GetMapping;
import com.example.servlettutorial.annotation.PostMapping;
import com.example.servlettutorial.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String username = request.getParameter("username");
        response.getWriter().println(String.format("Welcome, user %s", username));
    }

}
