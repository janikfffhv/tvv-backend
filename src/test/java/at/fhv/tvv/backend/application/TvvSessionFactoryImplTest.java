package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.rmi.TvvSession;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class TvvSessionFactoryImplTest {

    @Test
    void SessionMittelsTvvSessionFactoryErstellen() throws RemoteException {
        System.out.println("-------------------------------------Test 1 SessionMittelsTvvSessionFactoryErstellen-------------------------");


        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSessionImpl tst = tvvSessionFactory.createSession();

        assertEquals(TvvSessionImpl.class, tst.getClass());
    }


}