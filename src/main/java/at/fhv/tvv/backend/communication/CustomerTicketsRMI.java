package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.CustomerSearchTicketsImpl;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.rmi.CustomerSearch;
import at.fhv.tvv.shared.rmi.CustomerTickets;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class CustomerTicketsRMI extends UnicastRemoteObject implements CustomerTickets {
    private final CustomerSearchTicketsImpl customerSearchTickets;

    public CustomerTicketsRMI(CustomerSearchTicketsImpl customerSearchTickets) throws RemoteException {
        super();
        this.customerSearchTickets = customerSearchTickets;
    }

    @Override
    public CustomerInfoDTO searchById(UUID uuid) throws RemoteException {
        System.out.println("Ist hier!");
        return customerSearchTickets.searchTickets(uuid);
    }
}
