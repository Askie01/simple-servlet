package com.example.servlettutorial.validator;

public interface Validator<T> {
    boolean validate(T object);
}
