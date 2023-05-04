package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class EventDescriptionDTO implements Serializable {
    private final int eventId;

    private final String name;

    private final String veranstaltungsserie;
    private final String veranstalter;
    private final String kategorie;

    private final int datum;
    private final String gebaeude;
    private final String strasse;
    private final String hausnummer;
    private final int plz;
    private final String ort;
    private final String raum;

    private final String beschreibung;
    private final List<PlatzDTO> plaetze;

    public EventDescriptionDTO(int eventId, String name, String veranstaltungsserie, String veranstalter, String kategorie, int datum, String gebaeude, String strasse, String hausnummer, int plz, String ort, String raum, String beschreibung, List<PlatzDTO> plaetze) {
        this.eventId = eventId;
        this.name = name;
        this.veranstaltungsserie = veranstaltungsserie;
        this.veranstalter = veranstalter;
        this.kategorie = kategorie;
        this.datum = datum;
        this.gebaeude = gebaeude;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.raum = raum;
        this.beschreibung = beschreibung;
        this.plaetze = plaetze;
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

    public String getBeschreibung() {
        return beschreibung;
    }

    public List<PlatzDTO> getPlaetze() {
        return plaetze;
    }

    public String getVeranstalter() {
        return veranstalter;
    }

    public String getKategorie() {
        return kategorie;
    }

    public String getGebaeude() {
        return gebaeude;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public String getRaum() {
        return raum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDescriptionDTO that = (EventDescriptionDTO) o;
        return eventId == that.eventId && datum == that.datum && plz == that.plz && plaetze == that.plaetze && Objects.equals(name, that.name) && Objects.equals(veranstaltungsserie, that.veranstaltungsserie) && Objects.equals(veranstalter, that.veranstalter) && Objects.equals(kategorie, that.kategorie) && Objects.equals(gebaeude, that.gebaeude) && Objects.equals(strasse, that.strasse) && Objects.equals(hausnummer, that.hausnummer) && Objects.equals(ort, that.ort) && Objects.equals(raum, that.raum) && Objects.equals(beschreibung, that.beschreibung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, name, veranstaltungsserie, veranstalter, kategorie, datum, gebaeude, strasse, hausnummer, plz, ort, raum, beschreibung, plaetze);
    }
}


