package com.example.demo.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Wrong username or password!");
    }
}
