package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.rmi.TvvSession;
import at.fhv.tvv.shared.rmi.TvvSessionFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TvvSessionFactoryRMI extends UnicastRemoteObject implements TvvSessionFactory {

    private final TvvSessionFactory tvvSessionFactory;

    public TvvSessionFactoryRMI(TvvSessionFactory tvvSessionFactory) throws RemoteException {
        super();
        this.tvvSessionFactory = tvvSessionFactory;
    }

    @Override
    public TvvSession createSession() throws RemoteException {
        System.out.println("Wurde versucht!");
        return tvvSessionFactory.createSession();
    }
}
