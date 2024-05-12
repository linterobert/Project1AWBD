package com.example.demo;

import com.example.demo.Domain.Entities.Adresa;
import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Exceptions.AdresaNotFoundException;
import com.example.demo.Exceptions.CentruAdoptieNotFound;
import com.example.demo.Repositories.AdresaRepository;
import com.example.demo.Repositories.CentruAdoptieRepository;
import com.example.demo.Services.AdresaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Profile("mysql")
public class TestAdresaService {

    private static final Logger logger = LogManager.getLogger();
    @InjectMocks
    private AdresaService adresaService;
    @Mock
    private AdresaRepository adresaRepository;
    @Mock
    private CentruAdoptieRepository centruAdoptieRepository;

    @Test
    public void testCreateAdresaSuccess() {
        Adresa adresa = new Adresa("test strada", "test cod postal", "test bloc");
        Adresa adreaToSave = new Adresa(1, "test strada", "test cod postal", "test bloc");

        CentruAdoptie CentruAdoptie = new CentruAdoptie(1, "Test centru");
        Optional<CentruAdoptie> centruAdoptie = Optional.of(CentruAdoptie);

        when(centruAdoptieRepository.findById(1)).thenReturn(centruAdoptie);
        when(adresaRepository.save(adresa)).thenReturn(adreaToSave);

        //act
        Adresa adresaResult = adresaService.create(adresa, 1);

        //assert
        assertAdresaFields(adreaToSave, adresaResult);
    }

    @Test
    public void TestCreateAdresaThrowCentruNotFound() {
        Adresa adreaToSave = new Adresa("test strada", "test cod postal", "test bloc");
        CentruAdoptieNotFound centruAdoptieNotFound = new CentruAdoptieNotFound(1);

        when(centruAdoptieRepository.findById(1)).thenThrow(centruAdoptieNotFound);

        CentruAdoptieNotFound exception = assertThrows(CentruAdoptieNotFound.class,
                () -> adresaService.create(adreaToSave, 1));

        assertEquals("Nu exista centru de adoptie cu id-ul " + 1 + "!",
                exception.getMessage());
    }

    @Test
    public void TestUpdateAdresaThrowNotFound() {
        Adresa updatedAdresa = new Adresa(1,"update strada", "update cod postal", "update bloc");
        when(adresaRepository.findById(1)).thenThrow(new AdresaNotFoundException(1));

        AdresaNotFoundException exception = assertThrows(AdresaNotFoundException.class,
                () -> adresaService.update(updatedAdresa));

        assertEquals("Nu exista adresa cu id-ul " + 1 + "!",
                exception.getMessage());
    }

    @Test
    public void TestGetAdresaByIdSuccess() {
        Adresa adreaToReturn = new Adresa(1, "test strada", "test cod postal", "test bloc");
        when(adresaRepository.findById(1)).thenReturn(Optional.of(adreaToReturn));

        Adresa returnedAdresa = adresaService.getById(1);

        assertAdresaFields(adreaToReturn, returnedAdresa);
    }

    @Test
    public void TestGetAdresaThrowNotFound() {
        when(adresaRepository.findById(1)).thenThrow(new AdresaNotFoundException(1));

        AdresaNotFoundException exception = assertThrows(AdresaNotFoundException.class,
                () -> adresaService.getById(1));

        assertEquals("Nu exista adresa cu id-ul " + 1 + "!",
                exception.getMessage());
    }

    private static void assertAdresaFields(Adresa adresa, Adresa actualAdresa) {
        assertNotNull(actualAdresa);
        assertEquals(adresa.getId(), actualAdresa.getId());
        assertEquals(adresa.getStrada(), actualAdresa.getStrada());
        assertEquals(adresa.getCodPostal(), actualAdresa.getCodPostal());
        assertEquals(adresa.getBloc(), adresa.getBloc());
    }
}
