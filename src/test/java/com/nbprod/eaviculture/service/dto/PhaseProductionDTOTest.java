package com.nbprod.eaviculture.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class PhaseProductionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhaseProductionDTO.class);
        PhaseProductionDTO phaseProductionDTO1 = new PhaseProductionDTO();
        phaseProductionDTO1.setId(1L);
        PhaseProductionDTO phaseProductionDTO2 = new PhaseProductionDTO();
        assertThat(phaseProductionDTO1).isNotEqualTo(phaseProductionDTO2);
        phaseProductionDTO2.setId(phaseProductionDTO1.getId());
        assertThat(phaseProductionDTO1).isEqualTo(phaseProductionDTO2);
        phaseProductionDTO2.setId(2L);
        assertThat(phaseProductionDTO1).isNotEqualTo(phaseProductionDTO2);
        phaseProductionDTO1.setId(null);
        assertThat(phaseProductionDTO1).isNotEqualTo(phaseProductionDTO2);
    }
}
