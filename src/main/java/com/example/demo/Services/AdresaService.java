package com.example.demo.Services;

import com.example.demo.Domain.Entities.Adresa;
import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Exceptions.AdresaNotFoundException;
import com.example.demo.Exceptions.CentruAdoptieNotFound;
import com.example.demo.Repositories.AdresaRepository;
import com.example.demo.Repositories.CentruAdoptieRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdresaService {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private CentruAdoptieRepository centruAdoptieRepository;

    public Adresa create(Adresa adresa) {
        return adresaRepository.save(adresa);
    }

    public Adresa create(Adresa adresa, int id) {
        Optional<CentruAdoptie> centruAdoptie = centruAdoptieRepository.findById(id);
        if(centruAdoptie.isEmpty()) {
            logger.warn("Could not find centru adoptie with id " + id);
            logger.error("Could not create address");
            throw new CentruAdoptieNotFound(id);
        }
        adresa.setCentruAdoptie(centruAdoptie.get());
        return create(adresa);
    }

    public Adresa update(Adresa adresa) {
        Optional<Adresa> adresa1 = adresaRepository.findById(adresa.getId());
        if(adresa1.isEmpty()) {
            logger.warn("Could not find address with id " + adresa.getId());
            logger.error("Could not update address");
            throw new AdresaNotFoundException(adresa.getId());
        }
        adresa1.get().setBloc(adresa.getBloc());
        adresa1.get().setCodPostal(adresa.getCodPostal());
        adresa1.get().setStrada(adresa.getStrada());
        return adresaRepository.save(adresa1.get());
    }

    public Adresa getById(int id) {
        Optional<Adresa> adresa = adresaRepository.findById(id);
        if(adresa.isEmpty()) {
            logger.error("Could not find address with id " + id);
            throw new AdresaNotFoundException(id);
        }
        return adresa.get();
    }
}
