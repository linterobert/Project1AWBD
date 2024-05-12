package com.example.demo.DTOs.PetDTOs;

import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.WeightType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ToReturnPet {
    private int id;
    private String name;

    private PetType petType;

    private double weight;

    private WeightType weightUnitType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    private int centru_adoptie_id;
}
