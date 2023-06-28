package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.CustomerSearchDTO;

import javax.ejb.Local;
import java.util.List;
import java.util.UUID;

@Local
public interface CustomerSearchInt {
    List<CustomerSearchDTO> searchByString(String searchString);

    CustomerSearchDTO searchById(UUID id);
}
