package at.fhv.tvv.backend.interfaces;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;

import javax.ejb.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

@Local
public interface EventSearchInt {
    List<EventSearchDTO> searchByString(String searchString);
    List<EventSearchDTO> searchByDate(int searchDate1, int searchDate2);
    List<EventSearchDTO> searchByCategory(String searchString);
    EventDescriptionDTO searchById(int searchId);
}
