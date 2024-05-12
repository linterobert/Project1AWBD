package com.example.demo.Services;

import com.example.demo.Domain.Entities.CerereAdoptie;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.UserRole;
import com.example.demo.Exceptions.CerereAdoptieNotFound;
import com.example.demo.Exceptions.PetNotFoundException;
import com.example.demo.Exceptions.UnauthorizedUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.CerereAdoptieRepository;
import com.example.demo.Repositories.PetRepository;
import com.example.demo.Repositories.Security.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CerereAdoptieService {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private CerereAdoptieRepository cerereAdoptieRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;

    public CerereAdoptie save(CerereAdoptie cerereAdoptie) {
        return cerereAdoptieRepository.save(cerereAdoptie);
    }

    public CerereAdoptie save(CerereAdoptie cerereAdoptie, int petId, int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            logger.warn("Could not find user with id " + userId);
            logger.error("Could not create Cerere Adoptie");
            throw new UserNotFoundException();
        }

        List<Role> roles = userRepository.findRolesById(userId);
        roles = roles.stream().filter(role -> Objects.equals(role.getName(), "USER")).toList();

        if(roles.isEmpty()) {
            logger.warn("Utilizator neautorizat");
            throw new UnauthorizedUserException();
        }

        Optional<Pet> pet = petRepository.findById(petId);

        if(pet.isEmpty()) {
            logger.warn("Could not find pet with id " + petId);
            logger.error("Could not create Cerere Adoptie");
            throw new PetNotFoundException(petId);
        }

        cerereAdoptie.setUtilizator(user.get());
        cerereAdoptie.setPet(pet.get());
        return save(cerereAdoptie);
    }

    public CerereAdoptie getById(int id) {
        Optional<CerereAdoptie> cerereAdoptie = cerereAdoptieRepository.findById(id);
        if(cerereAdoptie.isEmpty()) {
            logger.error("Could not find Cerere Adoptie with id " + id);
            throw new CerereAdoptieNotFound(id);
        }
        return cerereAdoptie.get();
    }

    public CerereAdoptie update(CerereAdoptie cerereAdoptie, int id) {
        Optional<CerereAdoptie> cerereAdoptie1 = cerereAdoptieRepository.findById(id);

        if(cerereAdoptie1.isEmpty()) {
            logger.warn("Could not find Cerere Adoptie with id " + id);
            logger.error("Could not update Cerere Adoptie");
            throw new CerereAdoptieNotFound(id);
        }

        cerereAdoptie1.get().setStatusAdoptie(cerereAdoptie.getStatusAdoptie());
        cerereAdoptie1.get().setComment(cerereAdoptie.getComment());
        return cerereAdoptieRepository.save(cerereAdoptie1.get());
    }

    public List<CerereAdoptie> getByUserId(int userId) {
        return cerereAdoptieRepository.getCerereAdoptieByUtilizatorId(userId);
    }
}
