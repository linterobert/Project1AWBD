package com.example.demo.Domain.Entities;


import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.WeightType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PetType type;

    private double weight;

    @Enumerated(EnumType.ORDINAL)
    private WeightType weightUnit;

    private Date birthdate;

    @OneToMany(
            mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CerereAdoptie> cereriAdoptie;

    @ManyToOne
    @JoinColumn(name = "centru_adoptie_id")
    private CentruAdoptie centruAdoptie;

    @OneToMany(
            mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PetImage> images;

    public Pet(int id, String name, PetType type, double weight, WeightType weightUnit, Date birthdate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.birthdate = birthdate;
    }

    public Pet(String name, PetType type, double weight, WeightType weightUnit, Date birthdate) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.birthdate = birthdate;
    }

    public Pet() {

    }
}
