package com.example.servlettutorial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RequestAttributeReceiverServlet", urlPatterns = "/request-attribute-receiver")
public class RequestAttributeReceiverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        final String fullName = (String) request.getAttribute("full_name");
        System.out.println("Received full name '" + fullName + "'");
    }
}
