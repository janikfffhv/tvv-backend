package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class EventSearchDTO implements Serializable {
    private final int eventId;

    private final String name;

    private final String veranstaltungsserie;

    private final int datum;

    private final String ort;

    private final int plaetzeGesamt;

    private final int plaetzeVerfuegbar;

    public EventSearchDTO(int eventId, String name, String veranstaltungsserie, int datum, String ort, int plaetzeGesamt, int plaetzeVerfuegbar) {
        this.eventId = eventId;
        this.name = name;
        this.veranstaltungsserie = veranstaltungsserie;
        this.datum = datum;
        this.ort = ort;
        this.plaetzeGesamt = plaetzeGesamt;
        this.plaetzeVerfuegbar = plaetzeVerfuegbar;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getVeranstaltungsserie() {
        return veranstaltungsserie;
    }

    public int getDatum() {
        return datum;
    }

    public String getOrt() {
        return ort;
    }

    public int getPlaetzeGesamt() {
        return plaetzeGesamt;
    }

    public int getPlaetzeVerfuegbar() {
        return plaetzeVerfuegbar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventSearchDTO that = (EventSearchDTO) o;
        return eventId == that.eventId && datum == that.datum && plaetzeGesamt == that.plaetzeGesamt && plaetzeVerfuegbar == that.plaetzeVerfuegbar && Objects.equals(name, that.name) && Objects.equals(veranstaltungsserie, that.veranstaltungsserie) && Objects.equals(ort, that.ort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, name, veranstaltungsserie, datum, ort, plaetzeGesamt, plaetzeVerfuegbar);
    }
}
