package com.example.servlettutorial;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        log.atInfo().log("/hello GET request has been received.");
        response.setContentType("text/html");
        final PrintWriter printWriter = response.getWriter();
        printWriter.println("Thanks for sending the GET request.");
        printWriter.println("Much thanks for the data :)");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        log.atInfo().log("/hello POST request has been received.");
    }
}