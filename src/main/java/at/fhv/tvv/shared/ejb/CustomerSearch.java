package at.fhv.tvv.shared.ejb;
import at.fhv.tvv.shared.dto.CustomerSearchDTO;

import javax.ejb.Remote;
import java.util.List;
import java.util.UUID;

@Remote
public interface CustomerSearch {
    List<CustomerSearchDTO> searchByString(String searchString);
    CustomerSearchDTO searchById(UUID id);
}
