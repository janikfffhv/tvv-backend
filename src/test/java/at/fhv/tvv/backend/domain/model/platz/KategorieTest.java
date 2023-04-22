package at.fhv.tvv.backend.domain.model.platz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KategorieTest {

    @Test
    void sitzplatzKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.SITZPLATZ;

        //Test gültig, wenn...
        assertEquals("SITZPLATZ", test.getName());

    }

    @Test
    void stehplatzKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.STEHPLATZ;

        //Test gültig, wenn...
        assertEquals("STEHPLATZ", test.getName());

    }

    @Test
    void vipplatzKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.VIP;

        //Test gültig, wenn...
        assertEquals("VIP", test.getName());

    }

    @Test
    void platzKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.PLATZ; //Wenn es keine Platzkategorien gibt

        //Test gültig, wenn...
        assertEquals("PLATZ", test.getName());

    }

}