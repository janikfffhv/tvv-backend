package at.fhv.tvv.backend.domain.model.veranstaltungsort;
import javax.persistence.*;

@Entity
public class Veranstaltungsort {

    @Id
    @GeneratedValue
    private Long veranstaltungsortIdInternal;

    private int ortId;

    private String gebaeude;

    private String strasse;

    private String hausnummer;

    private int plz;

    private String ort;

    private String land;

    private String raum;

    protected Veranstaltungsort() {

    }

    public Veranstaltungsort(int ortId, String gebaeude, String strasse, String hausnummer, int plz, String ort, String land, String raum) {
        this.ortId = ortId;
        this.gebaeude = gebaeude;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.raum = raum;
    }

    public Long getVeranstaltungsortIdInternal() {
        return veranstaltungsortIdInternal;
    }

    public int getOrtId() {
        return ortId;
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

    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

    public String getRaum() {
        return raum;
    }
}
