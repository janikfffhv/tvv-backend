package at.fhv.tvv.shared.ejb;

import at.fhv.tvv.shared.dto.CustomerInfoDTO;

import javax.ejb.Remote;
import java.util.UUID;

@Remote
public interface CustomerTickets {
    CustomerInfoDTO searchById(UUID id);
}
