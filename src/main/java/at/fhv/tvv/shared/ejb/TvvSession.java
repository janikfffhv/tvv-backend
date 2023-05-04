package at.fhv.tvv.shared.ejb;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;

import javax.ejb.Remote;
import java.util.List;
import java.util.UUID;

@Remote
public interface TvvSession {
    List<WarenkorbZeileDTO> getWarenkorb();
    UUID getKunde();
    String getBenutzerName();
    String getZahlungsMethode();
    List<String> getRollen();
    List<String> getTopics();
    void hinzufuegen(WarenkorbZeileDTO warenkorbZeile);
    void loeschen (WarenkorbZeileDTO warenkorbZeile);
    void leeren();
    void hinzufuegenKunde(UUID kunde);
    void setBenutzerName(String name);
    void hinzufuegenZahlungsMethode(String zahlungsmethode);
    void setRollen(List<String> rollen);
    void setTopics(List<String> topics);
}
