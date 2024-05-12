package com.example.demo.Domain.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class CentruAdoptie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(
            mappedBy = "centruAdoptie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private List<Pet> pets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="adresa_id", referencedColumnName = "id")
    private Adresa adresa;

    public CentruAdoptie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CentruAdoptie(String name) {
        this.name = name;
    }

    public CentruAdoptie() {

    }
}
