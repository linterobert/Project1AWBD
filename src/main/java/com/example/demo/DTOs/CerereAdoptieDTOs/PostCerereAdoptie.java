package com.example.demo.DTOs.CerereAdoptieDTOs;

import com.example.demo.Domain.Enum.StatusAdoptie;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostCerereAdoptie {
    @Length(min = 5, max = 255)
    private String comment;
    @NotNull
    private int petId;
    @NotNull
    private int userId;
}
