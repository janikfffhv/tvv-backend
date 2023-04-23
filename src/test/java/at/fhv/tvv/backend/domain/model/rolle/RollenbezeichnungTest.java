package at.fhv.tvv.backend.domain.model.rolle;

import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RollenbezeichnungTest {

    @Test
    void chefRolleErstellen() {

        //Test-Rolle erstellen
        Rollenbezeichnung test = Rollenbezeichnung.CHEF;

        //Test gültig, wenn...
        assertEquals("CHEF", test.getName());

    }

    @Test
    void mitarbeiterRolleErstellen() {

        //Test-Rolle erstellen
        Rollenbezeichnung test = Rollenbezeichnung.MITARBEITER;

        //Test gültig, wenn...
        assertEquals("MITARBEITER", test.getName());

    }

}