package at.fhv.tvv.backend.domain.model.event;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long eventIdInternal;

    private int eventId;

    private String veranstaltungserie;

    private String name;

    private String beschreibung;

    private Date datum;

    private int ortId;

    private int plaetzeInsg;

    private boolean platzwahl;

    protected Event() {

    }

    public Event(int eventId, String veranstaltungserie, String name, String beschreibung, Date datum, int ortId, int plaetzeInsg, boolean platzwahl) {
        this.eventId = eventId;
        this.veranstaltungserie = veranstaltungserie;
        this.name = name;
        this.beschreibung = beschreibung;
        this.datum = datum;
        this.ortId = ortId;
        this.plaetzeInsg = plaetzeInsg;
        this.platzwahl = platzwahl;
    }

    public Long getEventIdInternal() {
        return eventIdInternal;
    }

    public int getEventId() {
        return eventId;
    }

    public String getVeranstaltungserie() {
        return veranstaltungserie;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Date getDatum() {
        return datum;
    }

    public int getOrtId() {
        return ortId;
    }

    public int getPlaetzeInsg() {
        return plaetzeInsg;
    }

    public boolean isPlatzwahl() {
        return platzwahl;
    }
}
