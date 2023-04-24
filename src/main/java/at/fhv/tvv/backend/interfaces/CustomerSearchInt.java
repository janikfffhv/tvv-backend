package at.fhv.tvv.backend.interfaces;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.dto.CustomerSearchDTO;

import javax.ejb.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Local
public interface CustomerSearchInt {
    List<CustomerSearchDTO> searchByString(String searchString);
    CustomerSearchDTO searchById(UUID id);
}
