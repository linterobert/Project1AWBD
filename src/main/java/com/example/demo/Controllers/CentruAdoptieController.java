package com.example.demo.Controllers;

import com.example.demo.DTOs.CentruAdoptieDTOs.PostCentruAdoptie;
import com.example.demo.DTOs.CentruAdoptieDTOs.PutCentruAdoptie;
import com.example.demo.DTOs.CentruAdoptieDTOs.ToReturnCentruAdoptie;
import com.example.demo.Domain.Entities.CentruAdoptie;
import com.example.demo.Mappers.CentruAdoptieMapper;
import com.example.demo.Services.CentruAdoptieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/centruAdoptie")
public class CentruAdoptieController {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private CentruAdoptieService centruAdoptieService;
    @Autowired
    private CentruAdoptieMapper centruAdoptieMapper;

    @PostMapping()
    public ResponseEntity<ToReturnCentruAdoptie> create(@RequestBody PostCentruAdoptie centruAdoptie) {
        logger.info("Start create centru adoptie");
        CentruAdoptie centruAdoptie1 = centruAdoptieService.save(centruAdoptieMapper.postCentruToCentru(centruAdoptie), centruAdoptie.getUserId());

        logger.info("Centru adoptie created successfully");
        return ResponseEntity.ok(centruAdoptieMapper.centruAdoptieToReturnCentruAdoptie(centruAdoptie1));
    }

    @GetMapping("/{id}")
    public ToReturnCentruAdoptie getCentruAdoptieById(@PathVariable("id") int id) {
        logger.info("Get centru with id " + id);
        return centruAdoptieMapper.centruAdoptieToReturnCentruAdoptie(centruAdoptieService.findById(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ToReturnCentruAdoptie> updateCentruAdoptie(@PathVariable("id") int id, @RequestBody PutCentruAdoptie putCentruAdoptie) {
        logger.info("Update centru with id " + id);
        CentruAdoptie centruAdoptie = centruAdoptieMapper.putCentruToCentru(putCentruAdoptie);
        centruAdoptie.setId(id);
        return ResponseEntity.ok(centruAdoptieMapper.centruAdoptieToReturnCentruAdoptie(centruAdoptieService.update(centruAdoptie)));
    }
}
