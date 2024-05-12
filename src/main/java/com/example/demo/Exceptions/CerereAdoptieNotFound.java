package com.example.demo.Exceptions;

public class CerereAdoptieNotFound extends RuntimeException{
    public CerereAdoptieNotFound(int id) {
        super("Nu exista cerere de adoptie cu id-ul " + id + "!");
    }
}
