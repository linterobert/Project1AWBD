package com.example.demo.Exceptions;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException(int id) {
        super("Pet with id " + id + " not found!");
    }
}
