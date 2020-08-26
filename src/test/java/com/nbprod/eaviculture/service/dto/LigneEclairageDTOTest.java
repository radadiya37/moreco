package com.nbprod.eaviculture.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class LigneEclairageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LigneEclairageDTO.class);
        LigneEclairageDTO ligneEclairageDTO1 = new LigneEclairageDTO();
        ligneEclairageDTO1.setId(1L);
        LigneEclairageDTO ligneEclairageDTO2 = new LigneEclairageDTO();
        assertThat(ligneEclairageDTO1).isNotEqualTo(ligneEclairageDTO2);
        ligneEclairageDTO2.setId(ligneEclairageDTO1.getId());
        assertThat(ligneEclairageDTO1).isEqualTo(ligneEclairageDTO2);
        ligneEclairageDTO2.setId(2L);
        assertThat(ligneEclairageDTO1).isNotEqualTo(ligneEclairageDTO2);
        ligneEclairageDTO1.setId(null);
        assertThat(ligneEclairageDTO1).isNotEqualTo(ligneEclairageDTO2);
    }
}
