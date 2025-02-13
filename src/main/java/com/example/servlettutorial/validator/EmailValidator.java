package com.example.servlettutorial.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailValidator implements Validator<String> {

    @Override
    public boolean validate(String email) {

        if (email == null) {
            log.atError().log("Email cannot be null.");
            throw new NullPointerException("Email cannot be null.");
        }

        final String regex = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        final boolean validEmail = email.matches(regex);

        if (validEmail) {
            System.out.println("Email is valid");
        } else {
            System.out.println("Email is not valid");
        }
        return validEmail;
    }
}
