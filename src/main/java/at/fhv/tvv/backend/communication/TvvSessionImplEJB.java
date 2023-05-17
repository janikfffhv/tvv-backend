package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.TvvSessionImpl;
import at.fhv.tvv.backend.interfaces.TvvSessionInt;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.ejb.TvvSession;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

@Stateful
@Remote(TvvSession.class)
public class TvvSessionImplEJB implements TvvSession {

    @EJB
    private TvvSessionInt tvvSession;

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
    public String getBenutzerName() {
        return tvvSession.getBenutzerName();
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
    public List<String> getRollen() {
        return tvvSession.getRollen();
    }

    @Override
    public List<String> getTopics() {
        return tvvSession.getTopics();
    }

    @Override
    public void hinzufuegenKunde(UUID uuid) {
        tvvSession.hinzufuegenKunde(uuid);
    }

    @Override
    public void setBenutzerName(String name) {
        tvvSession.setBenutzerName(name);
    }

    @Override
    public void hinzufuegenZahlungsMethode(String s) {
        tvvSession.hinzufuegenZahlungsMethode(s);

    }

    @Override
    public void setRollen(List<String> rollen) {
        tvvSession.setRollen(rollen);
    }

    @Override
    public void setTopics(List<String> topics) {
        tvvSession.setTopics(topics);
    }
}
