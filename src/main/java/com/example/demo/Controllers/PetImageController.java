package com.example.demo.Controllers;

import com.example.demo.DTOs.PetDTOs.PostPet;
import com.example.demo.DTOs.PetDTOs.ToReturnPet;
import com.example.demo.DTOs.PetImageDTOs.PostPetImage;
import com.example.demo.DTOs.PetImageDTOs.ToReturnPetImage;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.PetImage;
import com.example.demo.Mappers.PetImageMapper;
import com.example.demo.Services.PetImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/petImage")
public class PetImageController {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private PetImageService petImageService;
    @Autowired
    private PetImageMapper petImageMapper;

    @PostMapping("/create")
    public ResponseEntity<ToReturnPetImage> save(@RequestBody PostPetImage postPetImage) {
        logger.info("Start create pet image");
        PetImage petImage = petImageService.save(petImageMapper.postPetImageToPetImage(postPetImage), postPetImage.getPetId());

        ToReturnPetImage toReturnPet = petImageMapper.petImageToReturnPetImage(petImage);

        logger.info("Pet image created successfully");
        return ResponseEntity.created(URI.create("/petImage/"+ toReturnPet.getId()))
                .body(toReturnPet);
    }

    @GetMapping("/{id}")
    public ToReturnPetImage getPetImageById(@PathVariable("id") int id) {
        logger.info("Get pet image with id " + id);
        return petImageMapper.petImageToReturnPetImage(petImageService.getById(id));
    }

    @GetMapping("/petId/{id}")
    public List<ToReturnPetImage> getImagesByPetId(@PathVariable("id") int id) {
        logger.info("Get images for pet with id " + id);
        List<ToReturnPetImage> petImages = new ArrayList<>();

        petImageService.getByPetId(id).forEach(pet -> petImages.add(petImageMapper.petImageToReturnPetImage(pet)));

        return petImages;
    }
}
