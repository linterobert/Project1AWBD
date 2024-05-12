package com.example.demo.DTOs.PetDTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutPet {
    @Length(min = 5, max = 55)
    private String name;
    @NotNull
    private int petType;
    @Min(0)
    private double weight;
    @NotNull
    private int weightUnitType;
    @Past(message = "Data nu trebuie să fie în viitor")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthdate;
}
