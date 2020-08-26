package com.nbprod.eaviculture.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PhaseProductionMapperTest {

    private PhaseProductionMapper phaseProductionMapper;

    @BeforeEach
    public void setUp() {
        phaseProductionMapper = new PhaseProductionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(phaseProductionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(phaseProductionMapper.fromId(null)).isNull();
    }
}
