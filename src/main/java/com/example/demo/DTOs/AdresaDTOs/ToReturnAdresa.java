package com.example.demo.DTOs.AdresaDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ToReturnAdresa {
    private int id;
    private String strada;
    private String codPostal;
    private String bloc;
    private int centruAdoptie;
}
