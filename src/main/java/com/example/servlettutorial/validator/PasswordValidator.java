package com.example.servlettutorial.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator implements Validator<String> {

    @Override
    public boolean validate(String password) {

        if (password == null) {
            log.atError().log("Password cannot be null");
            throw new NullPointerException("Password cannot be null");
        }

        final String regex = "^.{6,}$";
        final boolean validPassword = password.matches(regex);

        if (validPassword) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is not valid");
        }
        return validPassword;
    }
}
