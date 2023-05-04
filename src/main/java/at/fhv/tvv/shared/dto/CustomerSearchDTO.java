package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class CustomerSearchDTO implements Serializable {
    private final UUID customerId;

    private final String vorname;
    private final String nachname;
    private final String geburtsdatum;
    private final String hausnummer;
    private final String strasse;
    private final int plz;
    private final String ort;
    private final String land;
    private final String telefonnummer;

    public CustomerSearchDTO(UUID customerId, String vorname, String nachname, String geburtsdatum, String hausnummer, String strasse, int plz, String ort, String land, String telefonnummer) {
        this.customerId = customerId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.hausnummer = hausnummer;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.telefonnummer = telefonnummer;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSearchDTO that = (CustomerSearchDTO) o;
        return plz == that.plz && Objects.equals(customerId, that.customerId) && Objects.equals(vorname, that.vorname) && Objects.equals(nachname, that.nachname) && Objects.equals(geburtsdatum, that.geburtsdatum) && Objects.equals(hausnummer, that.hausnummer) && Objects.equals(strasse, that.strasse) && Objects.equals(ort, that.ort) && Objects.equals(land, that.land) && Objects.equals(telefonnummer, that.telefonnummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, vorname, nachname, geburtsdatum, hausnummer, strasse, plz, ort, land, telefonnummer);
    }
}
