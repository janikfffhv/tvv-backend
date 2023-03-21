package at.fhv.tvv.backend.domain.model.ticket;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long ticketInternalId;

    private int ticketId;

    private int eventId;

    private float preis;

    private int verkaufsId;

    private int platzId;

    protected Ticket() {

    }

    public Ticket(int ticketId, int eventId, float preis, int verkaufsId, int platzId) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.preis = preis;
        this.verkaufsId = verkaufsId;
        this.platzId = platzId;
    }

    public Long getTicketInternalId() {
        return ticketInternalId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public float getPreis() {
        return preis;
    }

    public int getVerkaufsId() {
        return verkaufsId;
    }

    public int getPlatzId() {
        return platzId;
    }
}