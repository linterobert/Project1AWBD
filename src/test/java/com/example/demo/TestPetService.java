package com.example.demo;

import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.UserRole;
import com.example.demo.Domain.Enum.WeightType;
import com.example.demo.Exceptions.CentruAdoptieNotFound;
import com.example.demo.Exceptions.PetNotFoundException;
import com.example.demo.Exceptions.UnauthorizedUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.CentruAdoptieRepository;
import com.example.demo.Repositories.PetRepository;
import com.example.demo.Repositories.Security.UserRepository;
import com.example.demo.Services.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Profile("mysql")
public class TestPetService {
    @InjectMocks
    private PetService petService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CentruAdoptieRepository centruAdoptieRepository;
    @Mock
    private PetRepository petRepository;

    @Test
    public void testCreatePetSuccess() {
        Pet pet = new Pet("test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));
        Pet toSave = new Pet(1, "test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");
        CentruAdoptie centruAdoptie = new CentruAdoptie(1, "test name");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));
        when(centruAdoptieRepository.findById(1)).thenReturn(Optional.of(centruAdoptie));
        when(petRepository.save(pet)).thenReturn(toSave);

        Pet returned = petService.save(pet, 1, 1);
        assertPetFields(toSave, returned);
    }

    @Test
    public void testCreatePetThrowUserNotFound() {
        Pet pet = new Pet("test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> petService.save(pet, 1, 1));

        assertEquals("Wrong username or password!", exception.getMessage());
    }

    @Test
    public void testCreatePetThrowUnauthorized() {
        Pet pet = new Pet("test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("USER");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));

        UnauthorizedUserException exception = assertThrows(UnauthorizedUserException.class,
                () -> petService.save(pet, 1, 1));

        assertEquals("Utilizator neautorizat", exception.getMessage());
    }

    @Test
    public void testCreatePetThrowCentruNotFound() {
        Pet pet = new Pet("test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(centruAdoptieRepository.findById(1)).thenReturn(Optional.empty());
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));

        CentruAdoptieNotFound exception = assertThrows(CentruAdoptieNotFound.class,
                () -> petService.save(pet, 1, 1));

        assertEquals("Nu exista centru de adoptie cu id-ul " + 1 + "!",
                exception.getMessage());
    }

    @Test
    public void testGetPetByIdSuccess() {
        Pet pet = new Pet(1, "test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));

        when(petRepository.findById(1)).thenReturn(Optional.of(pet));

        Pet returned = petService.getById(1);

        assertPetFields(pet, returned);
    }

    @Test
    public void testGetPetByIdThrowNotFound() {
        when(petRepository.findById(1)).thenReturn(Optional.empty());

        PetNotFoundException exception = assertThrows(PetNotFoundException.class,
                () -> petService.getById(1));

        assertEquals("Pet with id " + 1 + " not found!", exception.getMessage());
    }

    @Test
    public void updatePetThrowNotFound() {
        Pet pet = new Pet("test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));
        when(petRepository.findById(1)).thenReturn(Optional.empty());

        PetNotFoundException exception = assertThrows(PetNotFoundException.class,
                () -> petService.update(pet, 1));

        assertEquals("Pet with id " + 1 + " not found!", exception.getMessage());
    }

    private static void assertPetFields(Pet pet, Pet actualPet) {
        assertNotNull(actualPet);
        assertEquals(pet.getId(), actualPet.getId());
        assertEquals(pet.getName(), actualPet.getName());
        assertEquals(pet.getType(), actualPet.getType());
        assertEquals(pet.getWeight(), actualPet.getWeight());
        assertEquals(pet.getBirthdate(), actualPet.getBirthdate());
        assertEquals(pet.getWeightUnit(), actualPet.getWeightUnit());
    }
}
