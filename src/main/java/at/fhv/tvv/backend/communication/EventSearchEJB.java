package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.interfaces.EventSearchInt;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EventSearchEJB implements at.fhv.tvv.shared.ejb.EventSearch {

    @EJB
    private EventSearchInt eventSearch;

    public EventSearchEJB() {
    }

    @Override
    public List<EventSearchDTO> searchByString(String searchString) {
        return eventSearch.searchByString(searchString);
    }

    @Override
    public List<EventSearchDTO> searchByDate(int searchDate1, int searchDate2) {
        return eventSearch.searchByDate(searchDate1, searchDate2);

    }

    @Override
    public List<EventSearchDTO> searchByCategory(String searchString) {
        return eventSearch.searchByCategory(searchString);
    }

    @Override
    public EventDescriptionDTO searchById(int searchId) {
        return eventSearch.searchById(searchId);
    }
}
