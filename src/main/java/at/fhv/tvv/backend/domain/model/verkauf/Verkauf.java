package at.fhv.tvv.backend.domain.model.verkauf;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Verkauf {

    @Id
    @GeneratedValue
    private Long verkaufsInternalId;

    private UUID verkaufsId;

    private float gesamtpreis;

    private float erstattungsbetrag;

    private UUID kundenId;

    private Date verkaufszeit;

    @Enumerated(EnumType.STRING)
    private Zahlungsmethode zahlungsmethode;

    private String angestellter;

    protected Verkauf() {

    }

    public Verkauf(UUID verkaufsId, float gesamtpreis, float erstattungsbetrag, UUID kundenId, Date verkaufszeit, Zahlungsmethode zahlungsmethode, String angestellter) {
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

    public Date getVerkaufszeit() {
        return verkaufszeit;
    }

    public Zahlungsmethode getZahlungsmethode() {
        return zahlungsmethode;
    }

    public String getAngestellter() {
        return angestellter;
    }
}
