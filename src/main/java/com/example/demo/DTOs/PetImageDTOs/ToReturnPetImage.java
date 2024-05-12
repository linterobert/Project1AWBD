package com.example.demo.DTOs.PetImageDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ToReturnPetImage {
    private int id;
    private String url;
    private String description;
    private int petId;
}
