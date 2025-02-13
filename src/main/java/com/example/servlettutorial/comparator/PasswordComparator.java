package com.example.servlettutorial.comparator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordComparator implements Comparator<String, String> {

    @Override
    public boolean compare(String firstPassword, String secondPassword) {

        if (firstPassword == null) {
            log.atError().log("firstPassword cannot be null");
            throw new NullPointerException("firstPassword cannot be null");
        }

        if (secondPassword == null) {
            log.atError().log("secondPassword cannot be null");
            throw new NullPointerException("secondPassword cannot be null");
        }

        final boolean equalPasswords = firstPassword.equals(secondPassword);
        if (equalPasswords) {
            System.out.println("Passwords are equal");
            return true;
        } else {
            System.out.println("Passwords are not equal");
            return false;
        }
    }
}
