package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.communication.TvvSessionImplRMI;
import at.fhv.tvv.shared.rmi.TvvSession;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class TvvSessionFactoryImplTest {

    @Test
    void SessionMittelsTvvSessionFactoryErstellen() throws RemoteException {
        System.out.println("-------------------------------------Test 1 SessionMittelsTvvSessionFactoryErstellen-------------------------");


        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession tst = tvvSessionFactory.createSession();

        assertEquals(TvvSessionImplRMI.class, tst.getClass());
    }


}