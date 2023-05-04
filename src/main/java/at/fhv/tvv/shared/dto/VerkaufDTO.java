package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class VerkaufDTO implements Serializable {
    private final float gesamtpreis;
    private final UUID kundenId;
    private final String zahlungsmethode;
    private final List<WarenkorbZeileDTO> plaetze;
    private final String verkaufszeit;

    public VerkaufDTO(float gesamtpreis, UUID kundenId, String zahlungsmethode, List<WarenkorbZeileDTO> plaetze, String verkaufszeit) {
        this.gesamtpreis = gesamtpreis;
        this.kundenId = kundenId;
        this.zahlungsmethode = zahlungsmethode;
        this.plaetze = plaetze;
        this.verkaufszeit = verkaufszeit;
    }

    public float getGesamtpreis() {
        return gesamtpreis;
    }

    public UUID getKundenId() {
        return kundenId;
    }

    public String getZahlungsmethode() {
        return zahlungsmethode;
    }

    public List<WarenkorbZeileDTO> getPlaetze() {
        return plaetze;
    }

    public String getVerkaufszeit() {
        return verkaufszeit;
    }
}
