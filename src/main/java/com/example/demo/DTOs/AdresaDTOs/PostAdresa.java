package com.example.demo.DTOs.AdresaDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class PostAdresa {
    @Length(min = 1, max = 55)
    private String strada;
    @Length(min = 5, max = 6)
    private String codPostal;
    @Length(min = 1, max = 3)
    private String bloc;
    private int centruAdoptie;
}
