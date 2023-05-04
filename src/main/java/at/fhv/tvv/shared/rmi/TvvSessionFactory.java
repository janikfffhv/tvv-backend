package at.fhv.tvv.shared.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TvvSessionFactory extends Remote {
    TvvSession createSession() throws RemoteException;
}
