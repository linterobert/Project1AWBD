package com.example.demo.Exceptions;

public class CentruAdoptieNotFound extends RuntimeException{
    public CentruAdoptieNotFound(int id) {
        super("Nu exista centru de adoptie cu id-ul " + id + "!");
    }
}
