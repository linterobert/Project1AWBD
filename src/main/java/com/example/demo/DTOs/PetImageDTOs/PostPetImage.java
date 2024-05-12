package com.example.demo.DTOs.PetImageDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@AllArgsConstructor
public class PostPetImage {
    @URL
    private String url;
    @Length(min = 0, max = 55)
    private String description;
    @NotNull
    private int petId;
}
