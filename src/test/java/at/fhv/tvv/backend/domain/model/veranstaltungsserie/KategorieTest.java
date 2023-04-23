package at.fhv.tvv.backend.domain.model.veranstaltungsserie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KategorieTest {

    @Test
    void kinoKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.KINO;

        //Test gültig, wenn...
        assertEquals("KINO", test.getName());

    }

    @Test
    void theaterKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.THEATER;

        //Test gültig, wenn...
        assertEquals("THEATER", test.getName());

    }

    @Test
    void konzertplatzKategorieErstellen() {

        //Test-Kategorie erstellen
        Kategorie test = Kategorie.KONZERT;

        //Test gültig, wenn...
        assertEquals("KONZERT", test.getName());

    }

}