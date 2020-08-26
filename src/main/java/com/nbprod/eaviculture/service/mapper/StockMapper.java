package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.StockDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Stock} and its DTO {@link StockDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockMapper extends EntityMapper<StockDTO, Stock> {



    default Stock fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stock stock = new Stock();
        stock.setId(id);
        return stock;
    }
}
