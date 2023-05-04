package at.fhv.tvv.shared.ejb;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface EventSearch {
    List<EventSearchDTO> searchByString(String searchString);
    List<EventSearchDTO> searchByDate(int searchDate1, int searchDate2);
    List<EventSearchDTO> searchByCategory(String searchString);
    EventDescriptionDTO searchById(int searchId);
}
