package com.nbprod.eaviculture.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class LogParametreEnvironementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogParametreEnvironementDTO.class);
        LogParametreEnvironementDTO logParametreEnvironementDTO1 = new LogParametreEnvironementDTO();
        logParametreEnvironementDTO1.setId(1L);
        LogParametreEnvironementDTO logParametreEnvironementDTO2 = new LogParametreEnvironementDTO();
        assertThat(logParametreEnvironementDTO1).isNotEqualTo(logParametreEnvironementDTO2);
        logParametreEnvironementDTO2.setId(logParametreEnvironementDTO1.getId());
        assertThat(logParametreEnvironementDTO1).isEqualTo(logParametreEnvironementDTO2);
        logParametreEnvironementDTO2.setId(2L);
        assertThat(logParametreEnvironementDTO1).isNotEqualTo(logParametreEnvironementDTO2);
        logParametreEnvironementDTO1.setId(null);
        assertThat(logParametreEnvironementDTO1).isNotEqualTo(logParametreEnvironementDTO2);
    }
}
