package at.fhv.tvv.shared.rmi;

import at.fhv.tvv.shared.dto.CustomerInfoDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface CustomerTickets extends Remote {
    CustomerInfoDTO searchById(UUID id) throws RemoteException;
}
