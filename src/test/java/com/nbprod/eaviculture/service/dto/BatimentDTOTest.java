package com.nbprod.eaviculture.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class BatimentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BatimentDTO.class);
        BatimentDTO batimentDTO1 = new BatimentDTO();
        batimentDTO1.setId(1L);
        BatimentDTO batimentDTO2 = new BatimentDTO();
        assertThat(batimentDTO1).isNotEqualTo(batimentDTO2);
        batimentDTO2.setId(batimentDTO1.getId());
        assertThat(batimentDTO1).isEqualTo(batimentDTO2);
        batimentDTO2.setId(2L);
        assertThat(batimentDTO1).isNotEqualTo(batimentDTO2);
        batimentDTO1.setId(null);
        assertThat(batimentDTO1).isNotEqualTo(batimentDTO2);
    }
}
