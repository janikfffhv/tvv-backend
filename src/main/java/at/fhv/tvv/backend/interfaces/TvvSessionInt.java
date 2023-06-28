package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;

import javax.ejb.Local;
import java.util.List;
import java.util.UUID;

@Local
public interface TvvSessionInt {
    List<WarenkorbZeileDTO> getWarenkorb();

    UUID getKunde();

    String getZahlungsMethode();

    String getBenutzerName();

    void setBenutzerName(String name);

    void hinzufuegen(WarenkorbZeileDTO warenkorbZeile);

    void loeschen(WarenkorbZeileDTO warenkorbZeile);

    void leeren();

    List<String> getRollen();

    void setRollen(List<String> rollen);

    List<String> getTopics();

    void setTopics(List<String> topics);

    void hinzufuegenKunde(UUID kunde);

    void hinzufuegenZahlungsMethode(String zahlungsmethode);
}
