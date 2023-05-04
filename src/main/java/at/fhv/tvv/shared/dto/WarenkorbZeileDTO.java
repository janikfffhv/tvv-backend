package at.fhv.tvv.shared.dto;

import java.io.Serializable;

public class WarenkorbZeileDTO implements Serializable {

    private final int platzId;
    private final String kategorie;
    private final int eventId;
    private final String eventName;
    private final float preis;
    private final String termin;

    public WarenkorbZeileDTO(int platzId, String kategorie, int eventId, String eventName, float preis, String termin) {
        this.platzId = platzId;
        this.kategorie = kategorie;
        this.eventId = eventId;
        this.eventName = eventName;
        this.preis = preis;
        this.termin = termin;
    }

    public int getPlatzId() {
        return platzId;
    }

    public String getKategorie() {
        return kategorie;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public float getPreis() {
        return preis;
    }

    public String getTermin() {
        return termin;
    }
}
