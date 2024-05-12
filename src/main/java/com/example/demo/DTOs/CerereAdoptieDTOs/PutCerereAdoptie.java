package com.example.demo.DTOs.CerereAdoptieDTOs;

import com.example.demo.Domain.Enum.StatusAdoptie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutCerereAdoptie {
    @Length(min = 5, max = 255)
    private String comment;
    @NotNull
    private StatusAdoptie statusAdoptie;
}
