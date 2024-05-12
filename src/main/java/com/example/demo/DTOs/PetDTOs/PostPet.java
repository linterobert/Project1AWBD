package com.example.demo.DTOs.PetDTOs;

import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.WeightType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class PostPet {
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
    @NotNull
    private int centru_adoptie_id;
    @NotNull
    private int user_id;
}
