package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.DepenseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Depense} and its DTO {@link DepenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {FournisseurMapper.class, FactureMapper.class})
public interface DepenseMapper extends EntityMapper<DepenseDTO, Depense> {

    @Mapping(source = "foursnisseur.id", target = "foursnisseurId")
    @Mapping(source = "facture.id", target = "factureId")
    DepenseDTO toDto(Depense depense);

    @Mapping(source = "foursnisseurId", target = "foursnisseur")
    @Mapping(source = "factureId", target = "facture")
    Depense toEntity(DepenseDTO depenseDTO);

    default Depense fromId(Long id) {
        if (id == null) {
            return null;
        }
        Depense depense = new Depense();
        depense.setId(id);
        return depense;
    }
}
