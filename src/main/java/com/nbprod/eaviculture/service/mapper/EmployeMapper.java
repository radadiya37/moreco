package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employe} and its DTO {@link EmployeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FonctionMapper.class})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {

    @Mapping(source = "fonction.id", target = "fonctionId")
    EmployeDTO toDto(Employe employe);

    @Mapping(source = "fonctionId", target = "fonction")
    Employe toEntity(EmployeDTO employeDTO);

    default Employe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setId(id);
        return employe;
    }
}
