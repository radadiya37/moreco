package com.nbprod.eaviculture.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class LigneEclairageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LigneEclairage.class);
        LigneEclairage ligneEclairage1 = new LigneEclairage();
        ligneEclairage1.setId(1L);
        LigneEclairage ligneEclairage2 = new LigneEclairage();
        ligneEclairage2.setId(ligneEclairage1.getId());
        assertThat(ligneEclairage1).isEqualTo(ligneEclairage2);
        ligneEclairage2.setId(2L);
        assertThat(ligneEclairage1).isNotEqualTo(ligneEclairage2);
        ligneEclairage1.setId(null);
        assertThat(ligneEclairage1).isNotEqualTo(ligneEclairage2);
    }
}
