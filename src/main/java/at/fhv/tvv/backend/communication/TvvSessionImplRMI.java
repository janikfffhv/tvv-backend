package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.TvvSessionImpl;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.rmi.TvvSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class TvvSessionImplRMI extends UnicastRemoteObject implements TvvSession {
    private final TvvSessionImpl tvvSession = new TvvSessionImpl();

    public TvvSessionImplRMI() throws RemoteException {
    }


    @Override
    public List<WarenkorbZeileDTO> getWarenkorb() throws RemoteException {
        return tvvSession.getWarenkorb();
    }

    @Override
    public UUID getKunde() throws RemoteException {
        return tvvSession.getKunde();
    }

    @Override
    public String getBenutzerName() throws RemoteException {
        return tvvSession.getBenutzerName();
    }

    @Override
    public String getZahlungsMethode() throws RemoteException {
        return tvvSession.getZahlungsMethode();
    }

    @Override
    public void hinzufuegen(WarenkorbZeileDTO warenkorbZeileDTO) throws RemoteException {
        tvvSession.hinzufuegen(warenkorbZeileDTO);
    }

    @Override
    public void loeschen(WarenkorbZeileDTO warenkorbZeileDTO) throws RemoteException {
        tvvSession.loeschen(warenkorbZeileDTO);
    }

    @Override
    public void leeren() throws RemoteException {
        tvvSession.leeren();
    }

    @Override
    public void hinzufuegenKunde(UUID uuid) throws RemoteException {
        tvvSession.hinzufuegenKunde(uuid);
    }

    @Override
    public void setBenutzerName(String name) throws RemoteException {
        tvvSession.setBenutzerName(name);
    }

    @Override
    public void hinzufuegenZahlungsMethode(String s) throws RemoteException {
        tvvSession.hinzufuegenZahlungsMethode(s);

    }
}
