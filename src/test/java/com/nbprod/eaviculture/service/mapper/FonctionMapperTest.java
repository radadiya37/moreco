package com.nbprod.eaviculture.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FonctionMapperTest {

    private FonctionMapper fonctionMapper;

    @BeforeEach
    public void setUp() {
        fonctionMapper = new FonctionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fonctionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fonctionMapper.fromId(null)).isNull();
    }
}
