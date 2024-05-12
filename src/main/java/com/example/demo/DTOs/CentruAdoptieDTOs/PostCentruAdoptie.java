package com.example.demo.DTOs.CentruAdoptieDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class PostCentruAdoptie {
    @Length(min = 5, max = 55)
    private String name;
    @NotNull
    private int userId;
}
