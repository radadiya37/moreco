package com.nbprod.eaviculture.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LigneEclairageMapperTest {

    private LigneEclairageMapper ligneEclairageMapper;

    @BeforeEach
    public void setUp() {
        ligneEclairageMapper = new LigneEclairageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ligneEclairageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ligneEclairageMapper.fromId(null)).isNull();
    }
}
