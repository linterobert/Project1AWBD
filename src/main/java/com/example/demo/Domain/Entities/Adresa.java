package com.example.demo.Domain.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Adresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String strada;
    private String codPostal;
    private String bloc;

    @OneToOne(mappedBy = "adresa")
    @JoinColumn(name = "centru_adoptie_id")
    private CentruAdoptie centruAdoptie;

    public Adresa() {
    }

    public Adresa(int id, String strada, String codPostal, String bloc) {
        this.id = id;
        this.strada = strada;
        this.codPostal = codPostal;
        this.bloc = bloc;
    }

    public Adresa(String strada, String codPostal, String bloc) {
        this.strada = strada;
        this.codPostal = codPostal;
        this.bloc = bloc;
    }

}
