package com.example.demo.Domain.Entities;

import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.StatusAdoptie;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class CerereAdoptie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    private StatusAdoptie statusAdoptie;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "utilizator_id")
    private UserEntity utilizator;

    public CerereAdoptie(String comment) {
        this.comment = comment;
        this.statusAdoptie = StatusAdoptie.OPEN;
        this.createdDate = LocalDateTime.now();
    }

    public CerereAdoptie(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public CerereAdoptie(int id, String comment, StatusAdoptie statusAdoptie, LocalDateTime createdDate) {
        this.id = id;
        this.comment = comment;
        this.statusAdoptie = statusAdoptie;
        this.createdDate = createdDate;
    }

    public CerereAdoptie() {
    }

    public CerereAdoptie(String comment, StatusAdoptie statusAdoptie) {
        this.comment = comment;
        this.statusAdoptie = statusAdoptie;
    }
}
