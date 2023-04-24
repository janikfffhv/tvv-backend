package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.CustomerSearchTicketsImpl;
import at.fhv.tvv.backend.interfaces.CustomerTicketsInt;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class CustomerTicketsEJB implements at.fhv.tvv.shared.ejb.CustomerTickets {
    private final CustomerTicketsInt customerSearchTickets = new CustomerSearchTicketsImpl();

    public CustomerTicketsEJB() {
    }

    @Override
    public CustomerInfoDTO searchById(UUID uuid) {
        System.out.println("Ist hier!");
        try {
            return customerSearchTickets.searchById(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
