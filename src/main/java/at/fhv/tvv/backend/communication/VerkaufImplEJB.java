package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.interfaces.VerkaufInt;
import at.fhv.tvv.shared.dto.VerkaufDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class VerkaufImplEJB implements at.fhv.tvv.shared.ejb.Verkauf {

    @EJB
    private VerkaufInt verkauf;

    public VerkaufImplEJB() {
    }

    @Override
    public boolean kaufe(VerkaufDTO verkaufDTO) {
        return verkauf.kaufe(verkaufDTO);
    }
}
