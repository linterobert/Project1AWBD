package com.example.demo.Controllers;

import com.example.demo.DTOs.AdresaDTOs.PostAdresa;
import com.example.demo.DTOs.AdresaDTOs.ToReturnAdresa;
import com.example.demo.DTOs.AdresaDTOs.UpdateAdresa;
import com.example.demo.Domain.Entities.Adresa;
import com.example.demo.Mappers.AdresaMapper;
import com.example.demo.Services.AdresaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/adresa")
public class AdresaController {
    private final static Logger logger = LogManager.getLogger();
    @Autowired
    private AdresaService adresaService;
    @Autowired
    private AdresaMapper adresaMapper;

    @PostMapping("/createAddress")
    public ResponseEntity<ToReturnAdresa> saveAddress(@RequestBody PostAdresa postAdresa) {
        logger.info("Start create address");
        Adresa adresa = adresaService.create(adresaMapper.postAdresaToAdresa(postAdresa), postAdresa.getCentruAdoptie());
        ToReturnAdresa toReturnAdresa = adresaMapper.adresaToToReturnAdresa(adresa);

        logger.info("Address created successfully");
        return ResponseEntity.created(URI.create("/adresa/"+ adresa.getId()))
                .body(toReturnAdresa);
    }

    @GetMapping("/{id}")
    public ToReturnAdresa getAdresaById(@PathVariable("id") int id) {
        logger.info("Get address with id " + id);
        return adresaMapper.adresaToToReturnAdresa(adresaService.getById(id));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ToReturnAdresa> updateAdresa(@PathVariable("id") int id, @RequestBody UpdateAdresa updateAdresa) {
        logger.info("Update address with id " + id);
        Adresa adresa = adresaMapper.updateAdresaToAdresa(updateAdresa);
        adresa.setId(id);
        adresa = adresaService.update(adresa);
        return ResponseEntity.ok(adresaMapper.adresaToToReturnAdresa(adresa));
    }
}
