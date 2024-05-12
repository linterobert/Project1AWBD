package com.example.demo.Services;

import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.PetImage;
import com.example.demo.Exceptions.PetImageNotFound;
import com.example.demo.Exceptions.PetNotFoundException;
import com.example.demo.Repositories.PetImageRepository;
import com.example.demo.Repositories.PetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetImageService {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private PetRepository petRepository;

    public PetImage save(PetImage petImage) {
        return petImageRepository.save(petImage);
    }

    public PetImage save(PetImage petImage, int petId) {
        Optional<Pet> pet = petRepository.findById(petId);

        if(pet.isEmpty()) {
            logger.warn("Could not find pet with id " + petId);
            logger.error("Could not create Pet Image");
            throw new PetNotFoundException(petId);
        }

        petImage.setPet(pet.get());
        return save(petImage);
    }

    public PetImage getById(int id) {
        Optional<PetImage> petImage = petImageRepository.findById(id);
        if(petImage.isEmpty()) {
            logger.error("Could not find pet image with id " + id);
            throw new PetImageNotFound(id);
        }
        return petImage.get();
    }

    public List<PetImage> getByPetId(int id) {
        return petImageRepository.getPetImagesByPetId(id);
    }
}
