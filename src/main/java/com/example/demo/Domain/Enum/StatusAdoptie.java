package com.example.demo.Domain.Enum;

public enum StatusAdoptie {
    OPEN, ACCEPTED, DECLINED;

    public StatusAdoptie fromId(int id) {
        switch (id) {
            case 0:
                return OPEN;
            case 1:
                return ACCEPTED;
            case 2:
                return DECLINED;
        }
        return null;
    }
}
