package com.example.demo.Exceptions;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException() {
        super("Utilizator neautorizat");
    }
}
