package at.fhv.tvv.backend.domain.model.angestellte;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Angestellte {

    @Id
    @GeneratedValue
    private Long angestellteInternalId;

    private String benutzername;

    private UUID passwort;

    private String vorname;

    private String nachname;

    private String rolle;

    protected Angestellte() {

    }

    public Angestellte(String benutzername, UUID passwort, String vorname, String nachname, String rolle) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.vorname = vorname;
        this.nachname = nachname;
        this.rolle = rolle;
    }

    public Long getAngestellteInternalId() {
        return angestellteInternalId;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public UUID getPasswort() {
        return passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getRolle() {
        return rolle;
    }
}
