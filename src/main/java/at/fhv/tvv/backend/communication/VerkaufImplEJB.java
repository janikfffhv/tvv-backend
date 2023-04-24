package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.interfaces.VerkaufInt;
import at.fhv.tvv.shared.dto.VerkaufDTO;
import at.fhv.tvv.shared.rmi.Verkauf;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Stateless
public class VerkaufImplEJB implements at.fhv.tvv.shared.ejb.Verkauf {

    @EJB
    private VerkaufInt verkauf;

    public VerkaufImplEJB() {
    }

    @Override
    public void kaufe(VerkaufDTO verkaufDTO) {
            verkauf.kaufe(verkaufDTO);
    }
}
