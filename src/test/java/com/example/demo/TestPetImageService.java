package com.example.demo;

import com.example.demo.Domain.Entities.Adresa;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.PetImage;
import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.WeightType;
import com.example.demo.Exceptions.AdresaNotFoundException;
import com.example.demo.Exceptions.PetImageNotFound;
import com.example.demo.Exceptions.PetNotFoundException;
import com.example.demo.Repositories.PetImageRepository;
import com.example.demo.Repositories.PetRepository;
import com.example.demo.Services.PetImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Profile("mysql")
public class TestPetImageService {
    @InjectMocks
    private PetImageService petImageService;
    @Mock
    private PetRepository petRepository;
    @Mock
    private PetImageRepository petImageRepository;

    @Test
    public void testCreatePetImageSuccess() {
        PetImage petImage = new PetImage("test url", "test description");
        PetImage petImageToSave = new PetImage(1, "test url", "test description");
        Pet pet = new Pet(1, "test name", PetType.CAT, 23.5, WeightType.LB, new Date(99, Calendar.NOVEMBER, 12));

        when(petRepository.findById(1)).thenReturn(Optional.of(pet));
        when(petImageRepository.save(petImage)).thenReturn(petImageToSave);

        PetImage returnedPet = petImageService.save(petImage, 1);

        assertEquals(petImageToSave, returnedPet);
    }

    @Test
    public void testCreatePetImageThrowNotFound() {
        PetImage petImage = new PetImage("test url", "test description");

        when(petRepository.findById(1)).thenReturn(Optional.empty());

        PetNotFoundException exception = assertThrows(PetNotFoundException.class,
                () -> petImageService.save(petImage, 1));

        assertEquals("Pet with id " + 1 + " not found!",
                exception.getMessage());
    }

    @Test
    public void testGetPetImageByIdSuccess() {
        PetImage petImageToSave = new PetImage(1, "test url", "test description");

        when(petImageRepository.findById(1)).thenReturn(Optional.of(petImageToSave));

        PetImage petImage = petImageService.getById(1);

        assertEquals(petImageToSave, petImage);
    }

    @Test
    public void testGetPetImageByIdThrowNotFound() {
        when(petImageRepository.findById(1)).thenReturn(Optional.empty());

        PetImageNotFound exception = assertThrows(PetImageNotFound.class,
                () -> petImageService.getById(1));

        assertEquals("Nu exista imagine cu id-ul " + 1 + "!",
                exception.getMessage());
    }

    private static void assertPetImageFields(PetImage petImage, PetImage actualPetImage) {
        assertNotNull(actualPetImage);
        assertEquals(petImage.getId(), actualPetImage.getId());
        assertEquals(petImage.getDescription(), actualPetImage.getDescription());
        assertEquals(petImage.getUrl(), actualPetImage.getUrl());
    }
}
