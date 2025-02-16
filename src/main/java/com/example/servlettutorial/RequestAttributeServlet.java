package com.example.servlettutorial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RequestAttributeServlet", urlPatterns = "/request-attribute")
public class RequestAttributeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        final String firstName = request.getParameter("first_name");
        final String lastName = request.getParameter("last_name");

        if (firstName == null) {
            System.out.println("First name is null");
        } else if (lastName == null) {
            System.out.println("Last name is null");
        } else {
            final String fullName = firstName + " " + lastName;
            request.setAttribute("full_name", fullName);
        }
        request
                .getRequestDispatcher("request-attribute-receiver")
                .forward(request, response);
    }
}
