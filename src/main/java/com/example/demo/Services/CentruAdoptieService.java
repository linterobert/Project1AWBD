package com.example.demo.Services;

import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Domain.Entities.Security.Role;
import com.example.demo.Domain.Entities.Security.UserEntity;
import com.example.demo.Domain.Enum.UserRole;
import com.example.demo.Exceptions.CentruAdoptieNotFound;
import com.example.demo.Exceptions.UnauthorizedUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.CentruAdoptieRepository;
import com.example.demo.Repositories.Security.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CentruAdoptieService {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private CentruAdoptieRepository centruAdoptieRepository;
    @Autowired
    private UserRepository userRepository;

    private CentruAdoptie save(CentruAdoptie centruAdoptie) {
        return centruAdoptieRepository.save(centruAdoptie);
    }

    public CentruAdoptie save(CentruAdoptie centruAdoptie, int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()){
            logger.warn("Could not find user with id " + userId);
            logger.error("Could not create Centru Adoptie");
            throw new UserNotFoundException();
        } else {
            List<Role> roles = userRepository.findRolesById(userId);
            roles = roles.stream().filter(role -> Objects.equals(role.getName(), "ADMIN")).toList();
            if(roles.isEmpty()) {
                logger.warn("Utilizator neautorizat");
                throw new UnauthorizedUserException();
            } else {
                return save(centruAdoptie);
            }
        }
    }

    public CentruAdoptie findById(int id) {
        Optional<CentruAdoptie> centruAdoptie = centruAdoptieRepository.findById(id);

        if( centruAdoptie.isPresent() ) {
            return centruAdoptie.get();
        } else {
            logger.error("Could not find Centru Adoptie with id " + id);
            throw  new CentruAdoptieNotFound(id);
        }
    }

    public CentruAdoptie update(CentruAdoptie centruAdoptie) {
        Optional<CentruAdoptie> toReturn = centruAdoptieRepository.findById(centruAdoptie.getId());

        if(toReturn.isEmpty()) {
            logger.warn("Could not find Centru Adoptie with id " + centruAdoptie.getId());
            logger.error("Could not update Centru Adoptie");
            throw new CentruAdoptieNotFound(centruAdoptie.getId());
        }

        toReturn.get().setName(centruAdoptie.getName());
        return centruAdoptieRepository.save(toReturn.get());
    }

}
