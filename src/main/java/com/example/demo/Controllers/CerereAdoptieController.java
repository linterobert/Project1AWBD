package com.example.demo.Controllers;

import com.example.demo.DTOs.AdresaDTOs.ToReturnAdresa;
import com.example.demo.DTOs.CentruAdoptieDTOs.PostCentruAdoptie;
import com.example.demo.DTOs.CentruAdoptieDTOs.PutCentruAdoptie;
import com.example.demo.DTOs.CentruAdoptieDTOs.ToReturnCentruAdoptie;
import com.example.demo.DTOs.CerereAdoptieDTOs.PostCerereAdoptie;
import com.example.demo.DTOs.CerereAdoptieDTOs.PutCerereAdoptie;
import com.example.demo.DTOs.CerereAdoptieDTOs.ToReturnCerereAdoptie;
import com.example.demo.DTOs.PetImageDTOs.ToReturnPetImage;
import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Domain.Entities.CerereAdoptie;
import com.example.demo.Mappers.CerereAdoptieMapper;
import com.example.demo.Services.CerereAdoptieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cerereAdoptie")
public class CerereAdoptieController {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private CerereAdoptieService cerereAdoptieService;
    @Autowired
    private CerereAdoptieMapper cerereAdoptieMapper;

    @PostMapping("/create")
    public ResponseEntity<ToReturnCerereAdoptie> create(@RequestBody PostCerereAdoptie postCerereAdoptie) {
        logger.info("Start create cerere adoptie");
        CerereAdoptie cerereAdoptie = cerereAdoptieService.save(cerereAdoptieMapper.postCerereAdoptieToCerereAdoptie(postCerereAdoptie), postCerereAdoptie.getPetId(), postCerereAdoptie.getUserId());

        logger.info("Cerere adoptie created successfully");
        return ResponseEntity.created(URI.create("/pet/"+ cerereAdoptie.getId()))
                .body(cerereAdoptieMapper.cerereAdoptieToReturnCerereAdoptie(cerereAdoptie));
    }

    @GetMapping("/{id}")
    public ToReturnCerereAdoptie getCerereAdoptieById(@PathVariable("id") int id) {
        logger.info("Get cerere with id " + id);
        return cerereAdoptieMapper.cerereAdoptieToReturnCerereAdoptie(cerereAdoptieService.getById(id));
    }

    @GetMapping("/userId/{id}")
    public List<ToReturnCerereAdoptie> getByUserId(@PathVariable("id") int id) {
        logger.info("Get cereri adoptie for user with id " + id);
        List<ToReturnCerereAdoptie> toReturnCerereAdopties = new ArrayList<>();

        cerereAdoptieService.getByUserId(id).forEach(cerere -> toReturnCerereAdopties.add(cerereAdoptieMapper.cerereAdoptieToReturnCerereAdoptie(cerere)));
        return toReturnCerereAdopties;
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ToReturnCerereAdoptie> updateCerereAdoptie(@PathVariable("id") int id, @RequestBody PutCerereAdoptie putCerereAdoptie) {
        logger.info("Update cerere adoptie with id " + id);
        CerereAdoptie cerereAdoptie = cerereAdoptieMapper.putCerereAdoptieToCerereAdoptie(putCerereAdoptie);
        cerereAdoptie.setId(id);
        return ResponseEntity.ok(cerereAdoptieMapper.cerereAdoptieToReturnCerereAdoptie(cerereAdoptieService.update(cerereAdoptie, id)));
    }
}
