package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.interfaces.CustomerTicketsInt;
import at.fhv.tvv.shared.dto.CustomerEventDTO;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.dto.CustomerSearchDTO;
import at.fhv.tvv.shared.rmi.CustomerSearch;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.Naming;
import java.util.List;
import java.util.UUID;
@Stateless
public class CustomerSearchTicketsImpl implements CustomerTicketsInt {
    @EJB
    private at.fhv.tvv.backend.domain.repository.EventRepository eventRepository;

    public CustomerSearchTicketsImpl() {

    }

    public CustomerInfoDTO searchById(UUID uuid) {
        System.out.println("Ist hier auch!!!");
        try {
            CustomerSearchDTO customer = null;
            try {
                CustomerSearch searchStub = (CustomerSearch) Naming.lookup("rmi://10.0.40.166/CustomerSearch");
                customer = searchStub.searchById(uuid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String customerName = customer.getVorname() + " " + customer.getNachname();
            String adresse = customer.getHausnummer() + " " + customer.getStrasse();
            String ort = customer.getPlz() + " " + customer.getOrt();
            System.out.println(uuid);
            List<CustomerEventDTO> events = eventRepository.getCustomerTickets(uuid);
            return new CustomerInfoDTO(customerName, customer.getGeburtsdatum(), adresse, ort, events);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
