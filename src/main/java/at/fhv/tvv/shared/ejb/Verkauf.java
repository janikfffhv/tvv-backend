package at.fhv.tvv.shared.ejb;

import at.fhv.tvv.shared.dto.VerkaufDTO;

import javax.ejb.Remote;

@Remote
public interface Verkauf {
    void kaufe(VerkaufDTO verkauf);
}
