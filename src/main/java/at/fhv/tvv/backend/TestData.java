package at.fhv.tvv.backend;

import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class TestData {
    private static final List<Veranstaltungsort> veranstaltungsorte = new ArrayList<>();

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        Veranstaltungsort dornbirn = new Veranstaltungsort(68501, "Kulturhaus Dornbirn", "Rathausplatz", "1", 6850, "Dornbirn", "Österreich", "Hauptsaal");
        veranstaltungsorte.add(dornbirn);


        EntityManager manager = HibernateService.entityManager();
        manager.getTransaction().begin();

        veranstaltungsorte.forEach(manager::persist);

        manager.getTransaction().commit();
    }
}
