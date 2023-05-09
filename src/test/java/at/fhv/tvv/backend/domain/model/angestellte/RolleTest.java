package at.fhv.tvv.backend.domain.model.angestellte;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RolleTest {

    @Test
    void mitarbeiterRolleKorrektErstellen() {

        //Test-Rolle Mitarbeiter erstellen
        Rolle test = Rolle.MITARBEITER;

        //Test gültig, wenn...
        assertEquals("Mitarbeiter", test.getName());

    }

    @Test
    void operatorRolleKorrektErstellen() {

        //Test-Rolle Operator erstellen
        Rolle test = Rolle.OPERATOR;

        //Test gültig, wenn...
        assertEquals("Operator", test.getName());

    }

}