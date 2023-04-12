package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.dto.VerkaufDTO;
import at.fhv.tvv.shared.rmi.Verkauf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VerkaufImplRMI extends UnicastRemoteObject implements Verkauf {

    private final Verkauf verkauf;

    public VerkaufImplRMI(Verkauf verkauf) throws RemoteException {
        super();
        this.verkauf = verkauf;
    }

    @Override
    public void kaufe(VerkaufDTO verkaufDTO) throws RemoteException {
        verkauf.kaufe(verkaufDTO);
    }
}
