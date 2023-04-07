package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.communication.TvvSessionImplRMI;
import at.fhv.tvv.shared.rmi.TvvSession;
import at.fhv.tvv.shared.rmi.TvvSessionFactory;

import java.rmi.RemoteException;

public class TvvSessionFactoryImpl implements TvvSessionFactory {

    @Override
    public TvvSession createSession() throws RemoteException {
        return new TvvSessionImplRMI();
    }

}
