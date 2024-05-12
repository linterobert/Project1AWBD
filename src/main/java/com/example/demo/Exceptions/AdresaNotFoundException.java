package com.example.demo.Exceptions;

public class AdresaNotFoundException extends RuntimeException{
    public AdresaNotFoundException(int id) {
        super("Nu exista adresa cu id-ul " + id + "!");
    }
}
