package com.nbprod.eaviculture.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DepenseMapperTest {

    private DepenseMapper depenseMapper;

    @BeforeEach
    public void setUp() {
        depenseMapper = new DepenseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(depenseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(depenseMapper.fromId(null)).isNull();
    }
}
