package at.fhv.tvv.backend.domain.model.rolle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RolleTest {

    @Test
    void rolleKorrektErstellen() {

        //Testdaten erstellen
        Rollenbezeichnung rollenbezeichnung = Rollenbezeichnung.MITARBEITER;
        Rollenbezeichnung rollenbezeichnung1 = Rollenbezeichnung.CHEF;

        //Test-Rolle erstellen
        Rolle test = new Rolle(rollenbezeichnung);
        Rolle test1 = new Rolle(rollenbezeichnung1);

        //Test gültig, wenn gilt...
        assertEquals(rollenbezeichnung, test.getRollenbezeichnung());
        assertEquals(rollenbezeichnung1, test1.getRollenbezeichnung());
        assertNull(test.getRolleInternalId()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.
        assertNull(test1.getRolleInternalId());

    }

}