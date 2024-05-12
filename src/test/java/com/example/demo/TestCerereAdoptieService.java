package com.example.demo;

import com.example.demo.Domain.Entities.CerereAdoptie;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.PetType;
import com.example.demo.Domain.Enum.StatusAdoptie;
import com.example.demo.Domain.Enum.UserRole;
import com.example.demo.Domain.Enum.WeightType;
import com.example.demo.Exceptions.CerereAdoptieNotFound;
import com.example.demo.Exceptions.PetNotFoundException;
import com.example.demo.Exceptions.UnauthorizedUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.CerereAdoptieRepository;
import com.example.demo.Repositories.PetRepository;
import com.example.demo.Repositories.Security.UserRepository;
import com.example.demo.Services.CerereAdoptieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Profile("mysql")
public class TestCerereAdoptieService {
    @InjectMocks
    private CerereAdoptieService cerereAdoptieService;
    @Mock
    private CerereAdoptieRepository cerereAdoptieRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PetRepository petRepository;

    @Test
    public void testCreateCerereAdoptieSuccess() {
        CerereAdoptie cerereAdoptie = new CerereAdoptie("test comment");
        CerereAdoptie toReturn = new CerereAdoptie(1, "test comment", StatusAdoptie.OPEN, LocalDateTime.now());
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        Pet pet = new Pet(1, "test name", PetType.CAT, 2.56, WeightType.KG, new Date(99, Calendar.NOVEMBER, 12));

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));
        when(petRepository.findById(1)).thenReturn(Optional.of(pet));
        when(cerereAdoptieRepository.save(cerereAdoptie)).thenReturn(toReturn);

        CerereAdoptie toSave = cerereAdoptieService.save(cerereAdoptie, 1, 1);

        assertEquals(toReturn, toSave);
    }

    @Test
    public void testCreateCerereAdoptieThrowUserNotFound() {
        CerereAdoptie cerereAdoptie = new CerereAdoptie("test comment");

        when(userRepository.findById(1)).thenThrow(new UserNotFoundException());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> cerereAdoptieService.save(cerereAdoptie, 1, 1));

        assertEquals("Wrong username or password!", exception.getMessage());
    }

    @Test
    public void testCreateCerereAdoptieThrowUnauthorized() {
        CerereAdoptie cerereAdoptie = new CerereAdoptie("test comment");
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));

        UnauthorizedUserException exception = assertThrows(UnauthorizedUserException.class,
                () -> cerereAdoptieService.save(cerereAdoptie, 1,1));

        assertEquals("Utilizator neautorizat", exception.getMessage());
    }

    @Test
    public void testCreateCerereThrowPetNotFound() {
        CerereAdoptie cerereAdoptie = new CerereAdoptie("test comment");
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("USER");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));
        when(petRepository.findById(1)).thenReturn(Optional.empty());

        PetNotFoundException exception = assertThrows(PetNotFoundException.class,
                () -> cerereAdoptieService.save(cerereAdoptie, 1, 1));

        assertEquals("Pet with id " + 1 + " not found!", exception.getMessage());
    }

    @Test
    public void testGetCerereByIdSuccess() {
        CerereAdoptie toReturn = new CerereAdoptie(1, "test comment", StatusAdoptie.OPEN, LocalDateTime.now());

        when(cerereAdoptieRepository.findById(1)).thenReturn(Optional.of(toReturn));

        CerereAdoptie retured = cerereAdoptieService.getById(1);

        assertCerereAdoptieFields(toReturn, retured);
    }

    @Test
    public void testGetCerereByIdThrowNotFound() {
        when(cerereAdoptieRepository.findById(1)).thenReturn(Optional.empty());

        CerereAdoptieNotFound exception = assertThrows(CerereAdoptieNotFound.class,
                () -> cerereAdoptieService.getById(1));

        assertEquals("Nu exista cerere de adoptie cu id-ul " + 1 + "!", exception.getMessage());
    }

    @Test
    public void testUpdateCerereAdoptieThrowNotFound() {
        CerereAdoptie updated = new CerereAdoptie("updated comment", StatusAdoptie.ACCEPTED);

        when(cerereAdoptieRepository.findById(1)).thenReturn(Optional.empty());

        CerereAdoptieNotFound exception = assertThrows(CerereAdoptieNotFound.class,
                () -> cerereAdoptieService.update(updated, 1));

        assertEquals("Nu exista cerere de adoptie cu id-ul " + 1 + "!", exception.getMessage());
    }

    private static void assertCerereAdoptieFields(CerereAdoptie cerereAdoptie, CerereAdoptie actualCerere) {
        assertNotNull(actualCerere);
        assertEquals(cerereAdoptie.getId(), actualCerere.getId());
        assertEquals(cerereAdoptie.getStatusAdoptie(), StatusAdoptie.OPEN);
        assertEquals(cerereAdoptie.getComment(), cerereAdoptie.getComment());
    }
}
