package at.fhv.tvv.shared.rmi;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;


public interface EventSearch extends Remote {
    List<EventSearchDTO> searchByString(String searchString) throws RemoteException;
    List<EventSearchDTO> searchByDate(int searchDate1, int searchDate2) throws RemoteException;
    List<EventSearchDTO> searchByCategory(String searchString) throws RemoteException;
    EventDescriptionDTO searchById(int searchId) throws RemoteException;
}
