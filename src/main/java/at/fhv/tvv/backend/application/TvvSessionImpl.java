package at.fhv.tvv.backend.application;

import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.rmi.TvvSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TvvSessionImpl implements TvvSession, Serializable {
    private List<WarenkorbZeileDTO> warenkorb = new ArrayList<>();
    @Override
    public List<WarenkorbZeileDTO> getWarenkorb() {
        return warenkorb;
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
}
