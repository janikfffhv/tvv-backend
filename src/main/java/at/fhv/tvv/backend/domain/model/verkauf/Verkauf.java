package at.fhv.tvv.backend.domain.model.verkauf;

import at.fhv.tvv.backend.domain.model.platz.Platz;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Verkauf {

    @Id
    @GeneratedValue
    private Long verkaufsInternalId;

    private UUID verkaufsId;

    private float gesamtpreis;

    private float erstattungsbetrag;

    @OneToMany(mappedBy = "verkauf")
    private List<Platz> plaetze = new ArrayList<>();

    private UUID kundenId;

    private String verkaufszeit;

    @Enumerated(EnumType.STRING)
    private Zahlungsmethode zahlungsmethode;

    private String angestellter;

    protected Verkauf() {

    }

    public Verkauf(UUID verkaufsId, float gesamtpreis, float erstattungsbetrag, UUID kundenId, String verkaufszeit, Zahlungsmethode zahlungsmethode, String angestellter) {
        this.verkaufsId = verkaufsId;
        this.gesamtpreis = gesamtpreis;
        this.erstattungsbetrag = erstattungsbetrag;
        this.kundenId = kundenId;
        this.verkaufszeit = verkaufszeit;
        this.zahlungsmethode = zahlungsmethode;
        this.angestellter = angestellter;
    }

    public Long getVerkaufsInternalId() {
        return verkaufsInternalId;
    }

    public UUID getVerkaufsId() {
        return verkaufsId;
    }

    public float getGesamtpreis() {
        return gesamtpreis;
    }

    public float getErstattungsbetrag() {
        return erstattungsbetrag;
    }

    public UUID getKundenId() {
        return kundenId;
    }

    public String getVerkaufszeit() {
        return verkaufszeit;
    }

    public Zahlungsmethode getZahlungsmethode() {
        return zahlungsmethode;
    }

    public String getAngestellter() {
        return angestellter;
    }

    public List<Platz> getPlaetze() {
        return plaetze;
    }

    public void addPlatz(Platz platz) {
        plaetze.add(platz);
        platz.setVerkauf(this);
    }
}
