package at.fhv.tvv.shared.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerInfoDTO implements Serializable {
    private final String name;
    private final String geburtsdatum;
    private final String adresse;
    private final String ort;
    private final List<CustomerEventDTO> tickets;

    public CustomerInfoDTO(String name, String geburtsdatum, String adresse, String ort, List<CustomerEventDTO> tickets) {
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
        this.ort = ort;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getOrt() {
        return ort;
    }

    public List<CustomerEventDTO> getTickets() {
        return tickets;
    }
}
