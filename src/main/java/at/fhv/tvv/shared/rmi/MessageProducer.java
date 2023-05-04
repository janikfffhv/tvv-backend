package at.fhv.tvv.shared.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageProducer extends Remote {
    void produce(String topic, String title, String message) throws RemoteException;
}
