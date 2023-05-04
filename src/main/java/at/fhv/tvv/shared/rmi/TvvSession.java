package at.fhv.tvv.shared.rmi;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface TvvSession extends Remote {
    List<WarenkorbZeileDTO> getWarenkorb() throws RemoteException;
    UUID getKunde() throws RemoteException;
    String getZahlungsMethode() throws RemoteException;
    String getBenutzerName() throws RemoteException;
    void hinzufuegen(WarenkorbZeileDTO warenkorbZeile) throws RemoteException;
    void loeschen (WarenkorbZeileDTO warenkorbZeile) throws RemoteException;
    void leeren() throws RemoteException;
    List<String> getRollen() throws RemoteException;
    List<String> getTopics() throws RemoteException;
    void hinzufuegenKunde(UUID kunde) throws RemoteException;
    void setBenutzerName(String name) throws RemoteException;
    void hinzufuegenZahlungsMethode(String zahlungsmethode) throws RemoteException;
    void setRollen(List<String> rollen) throws RemoteException;
    void setTopics(List<String> topics) throws RemoteException;
}
