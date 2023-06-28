package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.VerkaufDTO;

import javax.ejb.Local;

@Local
public interface VerkaufInt {
    boolean kaufe(VerkaufDTO verkauf);
}
