package com.example.servlettutorial.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgeValidator implements Validator<Integer> {

    @Override
    public boolean validate(Integer age) {

        if (age == null) {
            log.atError().log("Age cannot be null.");
            throw new NullPointerException("Age cannot be null.");
        }

        final boolean validAge = (age >= 18);
        if (validAge) {
            System.out.println("Age is valid");
        } else {
            System.out.println("Age is not valid");
        }
        return validAge;
    }
}
