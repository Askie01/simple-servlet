package com.example.servlettutorial.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameValidator implements Validator<String> {

    @Override
    public boolean validate(String name) {

        if (name == null) {
            log.atError().log("Name cannot be null.");
            throw new NullPointerException("Name cannot be null.");
        }

        final String regex = "^[A-Z][a-zA-Z ]*$";
        final boolean validName = name.matches(regex);

        if (validName) {
            System.out.println("Name is valid");
        } else {
            System.out.println("Name is not valid");
        }
        return validName;
    }
}
