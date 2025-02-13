package com.example.servlettutorial;

import com.example.servlettutorial.comparator.PasswordComparator;
import com.example.servlettutorial.validator.AgeValidator;
import com.example.servlettutorial.validator.EmailValidator;
import com.example.servlettutorial.validator.NameValidator;
import com.example.servlettutorial.validator.PasswordValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    private final NameValidator nameValidator = new NameValidator();
    private final EmailValidator emailValidator = new EmailValidator();
    private final AgeValidator ageValidator = new AgeValidator();
    private final PasswordValidator passwordValidator = new PasswordValidator();
    private final PasswordComparator passwordComparator = new PasswordComparator();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        final String email = request.getParameter("email");
        final String name = request.getParameter("name");
        final Integer age = Integer.valueOf(request.getParameter("age"));
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("confirm-password");

        final boolean validName = nameValidator.validate(name);
        final boolean validEmail = emailValidator.validate(email);
        final boolean validAge = ageValidator.validate(age);
        final boolean validPassword = passwordValidator.validate(password);
        final boolean equalPasswords = passwordComparator.compare(password, confirmPassword);
        final boolean validRegistration = validName &&
                validEmail &&
                validAge &&
                validPassword &&
                equalPasswords;
        if (validRegistration) {
            System.out.println("Registration successful");
        } else {
            System.out.println("Registration failed");
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request
                .getRequestDispatcher("register.jsp")
                .forward(request, response);
    }
}
