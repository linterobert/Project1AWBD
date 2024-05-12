package com.example.demo.Mappers;

import com.example.demo.DTOs.PetDTOs.PostPet;
import com.example.demo.DTOs.PetDTOs.PutPet;
import com.example.demo.DTOs.PetDTOs.ToReturnPet;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.WeightType;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    public Pet postPetToPet(PostPet postPet) {
        return new Pet(postPet.getName(), PetType.fromId(postPet.getPetType()), postPet.getWeight(), WeightType.fromId(postPet.getWeightUnitType()), postPet.getBirthdate());
    }

    public ToReturnPet petToReturnPet(Pet pet) {
        return new ToReturnPet(pet.getId(), pet.getName(), pet.getType(), pet.getWeight(), pet.getWeightUnit(), pet.getBirthdate(), pet.getCentruAdoptie().getId());
    }

    public Pet putPetToPet(PutPet putPet) {
        return new Pet(putPet.getName(), PetType.fromId(putPet.getPetType()), putPet.getWeight(), WeightType.fromId(putPet.getWeightUnitType()), putPet.getBirthdate());
    }
}
