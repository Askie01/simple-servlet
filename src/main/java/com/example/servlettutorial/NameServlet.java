package com.example.servlettutorial;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NameServlet", urlPatterns = "/name")
public class NameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        final String firstName = request.getParameter("first_name");
        final String lastName = request.getParameter("last_name");

        if (firstName == null || lastName == null) {
            System.out.println("A full name was not provided.");
        } else {
            System.out.println("Hello " + firstName + " " + lastName + ", nice to meet you.");
        }
    }
}
