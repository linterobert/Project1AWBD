package com.example.demo.Controllers;

import com.example.demo.DTOs.PetDTOs.PostPet;
import com.example.demo.DTOs.PetDTOs.PutPet;
import com.example.demo.DTOs.PetDTOs.ToReturnPet;
import com.example.demo.DTOs.SortRequest;
import com.example.demo.Domain.Entities.Pet;
import com.example.demo.Mappers.PetMapper;
import com.example.demo.Services.PetService;
import com.example.demo.consts.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private PetService petService;
    @Autowired
    private PetMapper petMapper;

    @PostMapping("/create")
    public ResponseEntity<ToReturnPet> save(@RequestBody PostPet postPet) {
        logger.info("Start create pet");
        Pet pet = petService.save(petMapper.postPetToPet(postPet), postPet.getUser_id(), postPet.getCentru_adoptie_id());

        ToReturnPet toReturnPet = petMapper.petToReturnPet(pet);

        logger.info("Pet created successfully");
        return ResponseEntity.created(URI.create("/pet/"+ toReturnPet.getId()))
                .body(toReturnPet);
    }

    @GetMapping("/{id}")
    public ToReturnPet getPetById(@PathVariable("id") int id) {
        logger.info("Get pet with id " + id);
        return petMapper.petToReturnPet(petService.getById(id));
    }

    @GetMapping("/pageNumber/{pageNumber}")
    public List<ToReturnPet> getActivePetsByPage(@PathVariable("pageNumber") int pageNumber,
                                                 @RequestBody(required = false) SortRequest sortedBy) {
        logger.info("Get pets by page and sorted");
        List<ToReturnPet> pets = new ArrayList<>();
        Pageable p;
        if(sortedBy != null) {
            Sort sort;
            if(sortedBy.getAscending()) {
                sort = Sort.by(sortedBy.getField_name()).ascending();
            } else {
                sort = Sort.by(sortedBy.getField_name()).descending();
            }
            p = PageRequest.of(pageNumber, Constants.PET_PAGE_SIZE, sort);
        } else {
            p = PageRequest.of(pageNumber, Constants.PET_PAGE_SIZE);
        }

        petService.findByPage(p).forEach(pet -> pets.add(petMapper.petToReturnPet(pet)));
        return pets;
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ToReturnPet> updatePet(@PathVariable("id") int id, @RequestBody PutPet putPet) {
        logger.info("Start update pet with id " + id);
        Pet pet = petMapper.putPetToPet(putPet);
        pet.setId(id);
        return ResponseEntity.ok(petMapper.petToReturnPet(petService.update(pet, id)));
    }
}
