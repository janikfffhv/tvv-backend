package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.dto.EventSearchDTO;
import at.fhv.tvv.shared.rmi.EventSearch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class EventSearchRMI extends UnicastRemoteObject implements EventSearch {

    private final EventSearch eventSearch;

    public EventSearchRMI(EventSearch eventSearch) throws RemoteException {
        super();
        this.eventSearch = eventSearch;
    }

    @Override
    public List<EventSearchDTO> searchByString(String searchString) throws RemoteException {
        return eventSearch.searchByString(searchString);
    }

    @Override
    public List<EventSearchDTO> searchByDate(String searchDate) throws RemoteException {
        return eventSearch.searchByDate(searchDate);
    }
}
