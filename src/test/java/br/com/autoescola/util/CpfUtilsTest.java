package br.com.autoescola.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CpfUtilsTest {

    @Test
    void deveAceitarCpfValido() {
        assertTrue(CpfUtils.isValid("52998224725"));
    }

    @Test
    void deveRejeitarCpfComDigitosRepetidos() {
        assertFalse(CpfUtils.isValid("11111111111"));
    }

    @Test
    void deveRejeitarCpfComDigitoVerificadorIncorreto() {
        assertFalse(CpfUtils.isValid("52998224724"));
    }
}
