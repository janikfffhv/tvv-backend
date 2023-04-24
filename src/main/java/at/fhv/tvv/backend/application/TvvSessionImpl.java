package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.interfaces.TvvSessionInt;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateful
public class TvvSessionImpl implements TvvSessionInt, Serializable {
    private List<WarenkorbZeileDTO> warenkorb = new ArrayList<>();
    private UUID kundenUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private String zahlungsmethode = "";

    @Override
    public List<WarenkorbZeileDTO> getWarenkorb() {
        return warenkorb;
    }

    @Override
    public UUID getKunde() {
        return kundenUUID;
    }

    @Override
    public String getZahlungsMethode() {
        return zahlungsmethode;
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
    public void hinzufuegenKunde(UUID uuid) {
        this.kundenUUID = uuid;
    }

    @Override
    public void hinzufuegenZahlungsMethode(String s) {
        this.zahlungsmethode = s;

    }


}
