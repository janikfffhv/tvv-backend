package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.CustomerInfoDTO;

import javax.ejb.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
@Local
public interface CustomerTicketsInt {
    CustomerInfoDTO searchById(UUID id);
}
