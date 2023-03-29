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
    public List<EventSearchDTO> searchByDate(int searchDate1, int searchDate2) throws RemoteException {
        return eventSearch.searchByDate(searchDate1, searchDate2);
    }

    @Override
    public List<EventSearchDTO> searchByCategory(String searchString) throws RemoteException {
        return eventSearch.searchByCategory(searchString);
    }
}
