package com.example.demo.Mappers;

import com.example.demo.DTOs.PetImageDTOs.PostPetImage;
import com.example.demo.DTOs.PetImageDTOs.ToReturnPetImage;
import com.example.demo.Domain.Entities.PetImage;
import com.example.demo.Services.PetImageService;
import org.springframework.stereotype.Component;

@Component
public class PetImageMapper {
    public PetImage postPetImageToPetImage(PostPetImage postPetImage) {
        return new PetImage(postPetImage.getUrl(), postPetImage.getDescription());
    }

    public ToReturnPetImage petImageToReturnPetImage(PetImage petImage) {
        return new ToReturnPetImage(petImage.getId(), petImage.getUrl(), petImage.getDescription(), petImage.getPet().getId());
    }
}
