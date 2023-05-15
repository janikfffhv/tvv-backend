package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.rmi.TvvSession;
import at.fhv.tvv.shared.rmi.TvvSessionFactory;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class TvvSessionFactoryRMITest {

    @Test
    void SessionMittelsTvvSessionFactoryErstellen() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplRMI.class, test.getClass());

    }

}