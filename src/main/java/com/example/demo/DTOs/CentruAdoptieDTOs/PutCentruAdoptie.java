package com.example.demo.DTOs.CentruAdoptieDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PutCentruAdoptie {
    @Length(min = 5, max = 55)
    private String name;
}
