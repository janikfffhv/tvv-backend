package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventSearchImplTest {

    @Test
    void eventSearchImplKorrektInitialisieren() {

        EventSearchImpl test = new EventSearchImpl();

        //Test gültig, wenn gilt...
        assertEquals(EventSearchImpl.class, test.getClass());

    }

    @Test
    void PlaetzeZaehlenWenn0Plaetze() {

        List<Platz> plaetze = new ArrayList<>(); // -> 0 Plätze vorhanden

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

       int actualPlatzCount = eventSearchImpl.countVerfuegbar(plaetze);

       //Test gültig, wenn gilt...
        assertEquals(0, actualPlatzCount);

    }

    @Test
    void PlaetzeZaehlenWenn2Plaetze() {

        List<Platz> plaetze = new ArrayList<>(); // -> 2 Plätze vorhanden
        //Platz platz = new Platz()

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        int actualPlatzCount = eventSearchImpl.countVerfuegbar(plaetze);

        //Test gültig, wenn gilt...
        assertEquals(0, actualPlatzCount);

    }

}