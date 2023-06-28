package at.fhv.tvv.backend.domain.model.platz;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    private float preis;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Verkauf verkauf;

    public Platz() {

    }

    public Platz(int platzId, int nummer, int reihe, Kategorie kategorie, float preis) {
        this.platzId = platzId;
        this.nummer = nummer;
        this.reihe = reihe;
        this.kategorie = kategorie;
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

    public float getPreis() {
        return preis;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Verkauf getVerkauf() {
        return verkauf;
    }

    public void setVerkauf(Verkauf verkauf) {
        this.verkauf = verkauf;
    }


}
