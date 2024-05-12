package com.example.demo.Mappers;

import com.example.demo.DTOs.CerereAdoptieDTOs.PostCerereAdoptie;
import com.example.demo.DTOs.CerereAdoptieDTOs.PutCerereAdoptie;
import com.example.demo.DTOs.CerereAdoptieDTOs.ToReturnCerereAdoptie;
import com.example.demo.Domain.Entities.CerereAdoptie;
import org.springframework.stereotype.Component;

@Component
public class CerereAdoptieMapper {
    public CerereAdoptie postCerereAdoptieToCerereAdoptie(PostCerereAdoptie postCerereAdoptie) {
        return new CerereAdoptie(postCerereAdoptie.getComment());
    }

    public CerereAdoptie putCerereAdoptieToCerereAdoptie(PutCerereAdoptie putCerereAdoptie) {
        return new CerereAdoptie(putCerereAdoptie.getComment(), putCerereAdoptie.getStatusAdoptie());
    }

    public ToReturnCerereAdoptie cerereAdoptieToReturnCerereAdoptie(CerereAdoptie cerereAdoptie) {
        return new ToReturnCerereAdoptie(cerereAdoptie.getId(), cerereAdoptie.getComment(), cerereAdoptie.getStatusAdoptie(), cerereAdoptie.getCreatedDate(), cerereAdoptie.getPet().getId(), cerereAdoptie.getUtilizator().getId());
    }
}
