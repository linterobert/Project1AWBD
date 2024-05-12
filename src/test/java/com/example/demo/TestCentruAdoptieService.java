package com.example.demo;


import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.UserRole;
import com.example.demo.Exceptions.CentruAdoptieNotFound;
import com.example.demo.Exceptions.UnauthorizedUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.CentruAdoptieRepository;
import com.example.demo.Repositories.Security.UserRepository;
import com.example.demo.Services.CentruAdoptieService;
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
public class TestCentruAdoptieService {
    @InjectMocks
    private CentruAdoptieService centruAdoptieService;
    @Mock
    private CentruAdoptieRepository centruAdoptieRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateCentruAdoptieSuccess() {
        CentruAdoptie centruAdoptie = new CentruAdoptie("test name");
        CentruAdoptie toSave = new CentruAdoptie(1, "test name");
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("ADMIN");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of(role));
        when(centruAdoptieRepository.save(centruAdoptie)).thenReturn(toSave);

        CentruAdoptie returnedCentru = centruAdoptieService.save(centruAdoptie, 1);

        assertEquals(toSave, returnedCentru);
    }

    @Test
    public void testCreateCentruAdoptieThrowNotFound() {
        CentruAdoptie centruAdoptie = new CentruAdoptie("test name");

        when(userRepository.findById(1)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> centruAdoptieService.save(centruAdoptie, 1));

        assertEquals("Wrong username or password!", exception.getMessage());
    }

    @Test
    public void testCreateCentruAdoptieThrowUnauthorized() {
        CentruAdoptie centruAdoptie = new CentruAdoptie("test name");
        UserEntity utilizator = new UserEntity();
        utilizator.setUsername("username");
        utilizator.setPassword("password");
        utilizator.setId(1);
        Role role = new Role();
        role.setId(1);
        role.setName("USER");

        when(userRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(userRepository.findRolesById(1)).thenReturn(List.of());

        UnauthorizedUserException exception = assertThrows(UnauthorizedUserException.class,
                () -> centruAdoptieService.save(centruAdoptie, 1));

        assertEquals("Utilizator neautorizat", exception.getMessage());
    }

    @Test
    public void testFindByIdSucces() {
        CentruAdoptie toSave = new CentruAdoptie(1, "test name");

        when(centruAdoptieRepository.findById(1)).thenReturn(Optional.of(toSave));

        CentruAdoptie toReturn = centruAdoptieService.findById(1);

        assertEquals(toSave, toReturn);
    }

    @Test
    public void testFindByIdThrowNotFound() {
        when(centruAdoptieRepository.findById(1)).thenThrow(new CentruAdoptieNotFound(1));

        CentruAdoptieNotFound exception = assertThrows(CentruAdoptieNotFound.class,
                () -> centruAdoptieService.findById(1));

        assertEquals("Nu exista centru de adoptie cu id-ul " + 1 + "!", exception.getMessage());
    }

    @Test
    public void testUpdateCentruSuccess() {
        CentruAdoptie toSave = new CentruAdoptie(1, "test name");
        CentruAdoptie toUpdate = new CentruAdoptie(1, "updated name");

        when(centruAdoptieRepository.findById(1)).thenReturn(Optional.of(toSave));
        when(centruAdoptieRepository.save(toUpdate)).thenReturn(toUpdate);

        CentruAdoptie toReturn = centruAdoptieService.update(toUpdate);

        assertEquals(toUpdate, toReturn);
    }

    @Test
    public void testUpdateCentruThrowNotFound() {
        CentruAdoptie toUpdate = new CentruAdoptie(1, "updated name");

        when(centruAdoptieRepository.findById(1)).thenThrow(new CentruAdoptieNotFound(1));

        CentruAdoptieNotFound exception = assertThrows(CentruAdoptieNotFound.class,
                () -> centruAdoptieService.update(toUpdate));

        assertEquals("Nu exista centru de adoptie cu id-ul " + 1 + "!", exception.getMessage());
    }

    private static void assertCentruFields(CentruAdoptie centruAdoptie, CentruAdoptie actualCentru) {
        assertNotNull(actualCentru);
        assertEquals(centruAdoptie.getId(), actualCentru.getId());
        assertEquals(centruAdoptie.getName(), actualCentru.getName());
    }
}
