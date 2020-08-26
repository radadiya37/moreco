package com.nbprod.eaviculture.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class PhaseProductionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhaseProduction.class);
        PhaseProduction phaseProduction1 = new PhaseProduction();
        phaseProduction1.setId(1L);
        PhaseProduction phaseProduction2 = new PhaseProduction();
        phaseProduction2.setId(phaseProduction1.getId());
        assertThat(phaseProduction1).isEqualTo(phaseProduction2);
        phaseProduction2.setId(2L);
        assertThat(phaseProduction1).isNotEqualTo(phaseProduction2);
        phaseProduction1.setId(null);
        assertThat(phaseProduction1).isNotEqualTo(phaseProduction2);
    }
}
