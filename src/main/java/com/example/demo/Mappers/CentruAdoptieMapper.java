package com.example.demo.Mappers;

import com.example.demo.DTOs.CentruAdoptieDTOs.PostCentruAdoptie;
import com.example.demo.DTOs.CentruAdoptieDTOs.PutCentruAdoptie;
import com.example.demo.DTOs.CentruAdoptieDTOs.ToReturnCentruAdoptie;
import com.example.demo.Domain.Entities.CentruAdoptie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CentruAdoptieMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    public CentruAdoptie postCentruToCentru(PostCentruAdoptie postCentruAdoptie) {
        return new CentruAdoptie(postCentruAdoptie.getName());
    }

    public ToReturnCentruAdoptie centruAdoptieToReturnCentruAdoptie(CentruAdoptie centruAdoptie) {
        return new ToReturnCentruAdoptie(centruAdoptie.getId(), centruAdoptie.getName());
    }

    public CentruAdoptie putCentruToCentru(PutCentruAdoptie putCentruAdoptie) {
        return new CentruAdoptie(putCentruAdoptie.getName());
    }
}
