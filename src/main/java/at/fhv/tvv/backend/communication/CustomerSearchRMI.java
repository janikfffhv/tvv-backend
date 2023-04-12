package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.CustomerSearchTicketsImpl;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.dto.CustomerSearchDTO;
import at.fhv.tvv.shared.rmi.CustomerSearch;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class CustomerSearchRMI extends UnicastRemoteObject implements CustomerSearch {
    public CustomerSearchRMI() throws RemoteException {
        super();
    }

    @Override
    public List<CustomerSearchDTO> searchByString(String s) throws RemoteException {
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
    public CustomerSearchDTO searchById(UUID uuid) throws RemoteException {
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
