package com.example.demo.Domain.Enum;

public enum PetType {
    CAT, DOG, PARROT, RABBIT;

    public static PetType fromId(int id) {
        switch (id) {
            case 0:
                return CAT;
            case 1:
                return DOG;
            case 2:
                return PARROT;
            case 3:
                return RABBIT;
        }
        return null;
    }
}
