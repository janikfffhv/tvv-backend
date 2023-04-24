package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;

import javax.ejb.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
@Local
public interface TvvSessionInt {
    List<WarenkorbZeileDTO> getWarenkorb();
    UUID getKunde();
    String getZahlungsMethode();
    void hinzufuegen(WarenkorbZeileDTO warenkorbZeile);
    void loeschen (WarenkorbZeileDTO warenkorbZeile);
    void leeren();

    void hinzufuegenKunde(UUID kunde);
    void hinzufuegenZahlungsMethode(String zahlungsmethode);
}
