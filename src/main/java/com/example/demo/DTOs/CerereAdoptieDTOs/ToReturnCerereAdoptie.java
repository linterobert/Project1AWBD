package com.example.demo.DTOs.CerereAdoptieDTOs;

import com.example.demo.Domain.Enum.StatusAdoptie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ToReturnCerereAdoptie {
    private int id;
    private String comment;

    private StatusAdoptie statusAdoptie;

    private LocalDateTime createdDate;
    private int petId;
    private int userId;
}
