package com.example.demo.Services;

import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.UserRole;
import com.example.demo.Exceptions.CentruAdoptieNotFound;
import com.example.demo.Exceptions.PetNotFoundException;
import com.example.demo.Exceptions.UnauthorizedUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.CentruAdoptieRepository;
import com.example.demo.Repositories.PetRepository;
import com.example.demo.Repositories.Security.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PetService {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CentruAdoptieRepository centruAdoptieRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet save(Pet pet, int user_id, int centruAdoptieId) {
        Optional<UserEntity> user = userRepository.findById(user_id);

        if(user.isEmpty()) {
            logger.warn("Could not find user with id " + user_id);
            logger.error("Could not create Pet");
            throw new UserNotFoundException();
        } else {
            List<Role> roles = userRepository.findRolesById(user_id);
            roles =roles.stream().filter(role -> Objects.equals(role.getName(), "ADMIN")).toList();
            if(!roles.isEmpty()) {
                Optional<CentruAdoptie> centruAdoptie = centruAdoptieRepository.findById(centruAdoptieId);
                if(centruAdoptie.isEmpty()) {
                    logger.warn("Could not find Centru Adoptie with id " + centruAdoptieId);
                    logger.error("Could not create Pet");
                    throw new CentruAdoptieNotFound(centruAdoptieId);
                } else {
                    pet.setCentruAdoptie(centruAdoptie.get());
                    return save(pet);
                }
            } else {
                logger.warn("Utilizator neautorizat");
                throw new UnauthorizedUserException();
            }
        }
    }

    public Pet update(Pet pet, int id) {
        Optional<Pet> pet1 = petRepository.findById(id);
        if(pet1.isEmpty()) {
            logger.warn("Could not find pet with id " + id);
            logger.error("Could not update pet");
            throw new PetNotFoundException(id);
        }

        pet1.get().setName(pet.getName());
        pet1.get().setBirthdate(pet.getBirthdate());
        pet1.get().setType(pet.getType());
        pet1.get().setWeight(pet.getWeight());
        pet1.get().setWeightUnit(pet.getWeightUnit());
        return petRepository.save(pet1.get());
    }

    public Pet getById(int id) {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isEmpty()) {
            logger.error("Could not find pet with id " + id);
            throw new PetNotFoundException(id);
        }
        return pet.get();
    }

    public List<Pet> findByPage(Pageable pageable) {
        return petRepository.getAll(pageable);
    }
}
