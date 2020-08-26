package com.nbprod.eaviculture.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LogParametreEnvironementMapperTest {

    private LogParametreEnvironementMapper logParametreEnvironementMapper;

    @BeforeEach
    public void setUp() {
        logParametreEnvironementMapper = new LogParametreEnvironementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(logParametreEnvironementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(logParametreEnvironementMapper.fromId(null)).isNull();
    }
}
