package com.example.demo.Mappers;

import com.example.demo.DTOs.AdresaDTOs.PostAdresa;
import com.example.demo.DTOs.AdresaDTOs.ToReturnAdresa;
import com.example.demo.DTOs.AdresaDTOs.UpdateAdresa;
import com.example.demo.Domain.Entities.Adresa;
import org.springframework.stereotype.Component;

@Component
public class AdresaMapper {
    public Adresa postAdresaToAdresa(PostAdresa postAdresa) {
        return new Adresa(postAdresa.getStrada(), postAdresa.getCodPostal(), postAdresa.getBloc());
    }

    public ToReturnAdresa adresaToToReturnAdresa(Adresa adresa) {
        if (adresa.getCentruAdoptie() != null) {
            return new ToReturnAdresa(adresa.getId(), adresa.getStrada(), adresa.getCodPostal(), adresa.getBloc(), adresa.getCentruAdoptie().getId());
        } else {
            return new ToReturnAdresa(adresa.getId(), adresa.getStrada(), adresa.getCodPostal(), adresa.getBloc(), 0);
        }
    }

    public Adresa updateAdresaToAdresa(UpdateAdresa updateAdresa) {
        return new Adresa(updateAdresa.getStrada(), updateAdresa.getCodPostal(), updateAdresa.getBloc());
    }
}
