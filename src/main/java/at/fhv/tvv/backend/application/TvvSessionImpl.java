package at.fhv.tvv.backend.application;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.rmi.TvvSession;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TvvSessionImpl implements TvvSession, Serializable {
    private List<WarenkorbZeileDTO> warenkorb = new ArrayList<>();
    private UUID kundenUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private String zahlungsmethode = "";

    private String benutzerName = "";

    @Override
    public List<WarenkorbZeileDTO> getWarenkorb() {
        return warenkorb;
    }

    @Override
    public UUID getKunde() throws RemoteException {
        return kundenUUID;
    }

    @Override
    public String getZahlungsMethode() throws RemoteException {
        return zahlungsmethode;
    }

    @Override
    public String getBenutzerName() throws RemoteException {
        return benutzerName;
    }

    @Override
    public void hinzufuegen(WarenkorbZeileDTO warenkorbZeileDTO) {
        warenkorb.add(warenkorbZeileDTO);
    }

    @Override
    public void loeschen(WarenkorbZeileDTO warenkorbZeileDTO) {
        warenkorb.remove(warenkorbZeileDTO);
    }

    @Override
    public void leeren() {
        warenkorb.clear();
    }

    @Override
    public void hinzufuegenKunde(UUID uuid) throws RemoteException {
        this.kundenUUID = uuid;
    }

    @Override
    public void setBenutzerName(String name) throws RemoteException {
        this.benutzerName = name;
    }

    @Override
    public void hinzufuegenZahlungsMethode(String s) throws RemoteException {
        this.zahlungsmethode = s;

    }


}
