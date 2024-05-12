package com.example.demo.Domain.Enum;


public enum UserRole {
    ADMIN, CLIENT;

    public static UserRole fromId(int id) {
        switch (id) {
            case 0:
                return ADMIN;
            case 1:
                return CLIENT;
        }
        return null;
    }
}
