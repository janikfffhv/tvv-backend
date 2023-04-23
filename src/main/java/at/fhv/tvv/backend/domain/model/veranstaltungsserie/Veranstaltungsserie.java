package at.fhv.tvv.backend.domain.model.veranstaltungsserie;

import javax.persistence.*;

@Entity
public class Veranstaltungsserie {

    @Id
    @GeneratedValue
    private Long veranstaltungsserieIdInternal;

    private String name;
    private String beschreibung;

    @Enumerated(EnumType.STRING)
    private Kategorie kategorie;

    private String veranstalter;


    protected Veranstaltungsserie() {

    }

    public Veranstaltungsserie(String name, String beschreibung, Kategorie kategorie, String veranstalter) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.veranstalter = veranstalter;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public String getVeranstalter() {
        return veranstalter;
    }

    public Long getVeranstaltungsserieInternalId() {
        return veranstaltungsserieIdInternal;
    }

}
