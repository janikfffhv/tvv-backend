package at.fhv.tvv.backend.domain.model.event;

import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long eventIdInternal;

    private int eventId;

    private String name;

    @ManyToOne
    private Veranstaltungsserie veranstaltungsserie;

    private String beschreibung;

    private int datum;

    @ManyToOne
    private Veranstaltungsort veranstaltungsort;

    @OneToMany(mappedBy="event")
    private List<Platz> plaetze = new ArrayList<>();


    protected Event() {

    }

    public Event(int eventId, String name, Veranstaltungsserie veranstaltungsserie, String beschreibung, int datum, Veranstaltungsort veranstaltungsort) {
        this.eventId = eventId;
        this.name = name;
        this.veranstaltungsserie = veranstaltungsserie;
        this.beschreibung = beschreibung;
        this.datum = datum;
        this.veranstaltungsort = veranstaltungsort;
    }

    public Long getEventInternalId() {
        return eventIdInternal;
    }

    public int getEventId() {
        return eventId;
    }


    public String getName() {
        return name;
    }


    public Veranstaltungsserie getVeranstaltungsserie() {
        return veranstaltungsserie;
    }


    public String getBeschreibung() {
        return beschreibung;
    }


    public int getDatum() {
        return datum;
    }


    public Veranstaltungsort getVeranstaltungsort() {
        return veranstaltungsort;
    }


    public List<Platz> getPlaetze() {
        return plaetze;
    }

    public void addPlatz(Platz platz) {
        plaetze.add(platz);
        platz.setEvent(this);
    }
}
