package org.db_crud.db_crud.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EscolaTest {

    @Test
    public void testaConstrutoresEGetters(){

        Escola escola = new Escola(1, "Escola Teste", "Localização Teste");

        assertEquals(1, escola.getId());
        assertEquals("Escola Teste", escola.getNome());
        assertEquals("Localização Teste", escola.getLocalizacao());
    }

    @Test
    public void testaEqualsEHashCode() {

        Escola escola1 = new Escola(1, "Escola Teste", "Localização Teste");
        Escola escola2 = new Escola(1, "Escola Teste", "Localização Teste");
        Escola escola3 = new Escola(2, "Outra Escola", "Outra Localização");

        assertEquals(escola1, escola2);
        assertEquals(escola1.hashCode(), escola2.hashCode());
        assertNotEquals(escola1, escola3);
        assertNotEquals(escola1.hashCode(), escola3.hashCode());
    }
}
