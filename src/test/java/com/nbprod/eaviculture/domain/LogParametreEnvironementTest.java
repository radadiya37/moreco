package com.nbprod.eaviculture.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.nbprod.eaviculture.web.rest.TestUtil;

public class LogParametreEnvironementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogParametreEnvironement.class);
        LogParametreEnvironement logParametreEnvironement1 = new LogParametreEnvironement();
        logParametreEnvironement1.setId(1L);
        LogParametreEnvironement logParametreEnvironement2 = new LogParametreEnvironement();
        logParametreEnvironement2.setId(logParametreEnvironement1.getId());
        assertThat(logParametreEnvironement1).isEqualTo(logParametreEnvironement2);
        logParametreEnvironement2.setId(2L);
        assertThat(logParametreEnvironement1).isNotEqualTo(logParametreEnvironement2);
        logParametreEnvironement1.setId(null);
        assertThat(logParametreEnvironement1).isNotEqualTo(logParametreEnvironement2);
    }
}
