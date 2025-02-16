package com.example.servlettutorial;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
