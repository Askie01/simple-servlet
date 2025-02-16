package com.example.servlettutorial;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "RequestBodyParserServlet", urlPatterns = "/request-body")
public class RequestBodyParserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        final BufferedReader reader = request.getReader();
        final StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        //  This one requires some external JSON parser in order to make it more natural.
        final String requestBody = stringBuilder.toString();
        System.out.println("Received request body: " + requestBody);
    }
}
