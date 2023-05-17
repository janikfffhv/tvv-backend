package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

@Local
public interface TvvSessionInt {
    List<WarenkorbZeileDTO> getWarenkorb();
    UUID getKunde();
    String getZahlungsMethode();
    String getBenutzerName();
    void hinzufuegen(WarenkorbZeileDTO warenkorbZeile);
    void loeschen (WarenkorbZeileDTO warenkorbZeile);
    void leeren();
    List<String> getRollen();
    List<String> getTopics();
    void hinzufuegenKunde(UUID kunde);
    void setBenutzerName(String name);
    void hinzufuegenZahlungsMethode(String zahlungsmethode);
    void setRollen(List<String> rollen);
    void setTopics(List<String> topics);
}
