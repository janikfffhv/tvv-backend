package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.UUID;

public class CustomerEventDTO implements Serializable {
    private final UUID ticketID;
    private final String verkaufsZeitpunkt;
    private final String zahlungsmethode;

    private final float gesamtPreis;

    public CustomerEventDTO(UUID ticketID, String verkaufsZeitpunkt, String zahlungsmethode, float gesamtPreis) {
        this.ticketID = ticketID;
        this.gesamtPreis = gesamtPreis;
        this.verkaufsZeitpunkt = verkaufsZeitpunkt;
        this.zahlungsmethode = zahlungsmethode;
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public String getVerkaufsZeitpunkt() {
        return verkaufsZeitpunkt;
    }

    public String getZahlungsmethode() {
        return zahlungsmethode;
    }

    public float getGesamtPreis() {
        return gesamtPreis;
    }
}
