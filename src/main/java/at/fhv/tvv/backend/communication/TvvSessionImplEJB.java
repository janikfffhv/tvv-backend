package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.TvvSessionImpl;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.ejb.TvvSession;

import javax.ejb.Stateful;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

@Stateful
public class TvvSessionImplEJB implements TvvSession {

    private final TvvSessionImpl tvvSession = new TvvSessionImpl();

    public TvvSessionImplEJB() {
    }


    @Override
    public List<WarenkorbZeileDTO> getWarenkorb() {
        return tvvSession.getWarenkorb();
    }

    @Override
    public UUID getKunde() {
        return tvvSession.getKunde();
    }

    @Override
    public String getZahlungsMethode() {
        return tvvSession.getZahlungsMethode();
    }

    @Override
    public void hinzufuegen(WarenkorbZeileDTO warenkorbZeileDTO) {
        tvvSession.hinzufuegen(warenkorbZeileDTO);
    }

    @Override
    public void loeschen(WarenkorbZeileDTO warenkorbZeileDTO) {
        tvvSession.loeschen(warenkorbZeileDTO);
    }

    @Override
    public void leeren() {
        tvvSession.leeren();
    }

    @Override
    public void hinzufuegenKunde(UUID uuid) {
        tvvSession.hinzufuegenKunde(uuid);
    }

    @Override
    public void hinzufuegenZahlungsMethode(String s) {
        tvvSession.hinzufuegenZahlungsMethode(s);

    }
}
