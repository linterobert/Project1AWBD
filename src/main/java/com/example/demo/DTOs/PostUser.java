package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PostUser {
    private String name;
    private String email;
    private int role;
    private String password;
}
