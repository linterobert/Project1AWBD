package com.example.demo.Domain.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PetImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public PetImage(int id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }

    public PetImage(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public PetImage() {
    }
}
