package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.dto.CustomerSearchDTO;
import at.fhv.tvv.shared.rmi.CustomerSearch;

import javax.ejb.Stateless;
import java.rmi.Naming;
import java.util.List;
import java.util.UUID;

@Stateless
public class CustomerSearchEJB implements at.fhv.tvv.shared.ejb.CustomerSearch {
    public CustomerSearchEJB() {
    }

    @Override
    public List<CustomerSearchDTO> searchByString(String s) {
        List<CustomerSearchDTO> customerList = null;
        try {
            CustomerSearch searchStub = (CustomerSearch) Naming.lookup("rmi://10.0.40.167/CustomerSearch");
            customerList = searchStub.searchByString(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public CustomerSearchDTO searchById(UUID uuid) {
        CustomerSearchDTO customer = null;
        try {
            CustomerSearch searchStub = (CustomerSearch) Naming.lookup("rmi://10.0.40.167/CustomerSearch");
            customer = searchStub.searchById(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
}
