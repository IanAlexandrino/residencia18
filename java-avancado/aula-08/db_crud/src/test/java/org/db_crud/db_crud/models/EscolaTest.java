package org.db_crud.db_crud.models;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EscolaTest {

    Escola escola1;
    Escola escola2;
    Escola escola3;

    static Faker faker = new Faker();

    public class ModelMock{
        public static final int idEscola = faker.number().randomDigit();
        public static final String nomeEscola = faker.company().name();
        public static final String endereco = faker.address().streetAddress();
    }

    @BeforeEach
    public void setUp(){
        escola1 = new Escola(ModelMock.idEscola, ModelMock.nomeEscola, ModelMock.endereco);
        escola2 = new Escola(escola1.getId(), escola1.getNome(), escola1.getLocalizacao());
        escola3 = new Escola(faker.number().randomDigit(), faker.company().name(), faker.address().streetAddress());
    }

    @Test
    public void testaConstrutoresEGetters(){
        assertEquals(ModelMock.idEscola, escola1.getId());
        assertEquals(ModelMock.nomeEscola, escola1.getNome());
        assertEquals(ModelMock.endereco, escola1.getLocalizacao());
    }

    @Test
    public void testaEqualsEHashCode() {
        assertEquals(escola1, escola2);
        assertEquals(escola1.hashCode(), escola2.hashCode());
        assertNotEquals(escola1, escola3);
        assertNotEquals(escola1.hashCode(), escola3.hashCode());
    }
}
