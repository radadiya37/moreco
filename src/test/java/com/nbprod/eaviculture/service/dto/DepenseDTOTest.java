package com.nbprod.eaviculture.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class DepenseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepenseDTO.class);
        DepenseDTO depenseDTO1 = new DepenseDTO();
        depenseDTO1.setId(1L);
        DepenseDTO depenseDTO2 = new DepenseDTO();
        assertThat(depenseDTO1).isNotEqualTo(depenseDTO2);
        depenseDTO2.setId(depenseDTO1.getId());
        assertThat(depenseDTO1).isEqualTo(depenseDTO2);
        depenseDTO2.setId(2L);
        assertThat(depenseDTO1).isNotEqualTo(depenseDTO2);
        depenseDTO1.setId(null);
        assertThat(depenseDTO1).isNotEqualTo(depenseDTO2);
    }
}
