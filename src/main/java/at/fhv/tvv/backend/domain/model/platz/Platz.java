package at.fhv.tvv.backend.domain.model.platz;

import javax.persistence.*;

@Entity
public class Platz {

    @Id
    @GeneratedValue
    private Long platzInternalId;

    private int platzId;

    private int nummer;

    private int reihe;

    @Enumerated(EnumType.STRING)
    private Kategorie kategorie;

    private int eventId;

    private float preis;

    protected Platz() {

    }

    public Platz(int platzId, int nummer, int reihe, Kategorie kategorie, int eventId, float preis) {
        this.platzId = platzId;
        this.nummer = nummer;
        this.reihe = reihe;
        this.kategorie = kategorie;
        this.eventId = eventId;
        this.preis = preis;
    }

    public Long getPlatzInternalId() {
        return platzInternalId;
    }

    public int getPlatzId() {
        return platzId;
    }

    public int getNummer() {
        return nummer;
    }

    public int getReihe() {
        return reihe;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public int getEventId() {
        return eventId;
    }

    public float getPreis() {
        return preis;
    }
}
