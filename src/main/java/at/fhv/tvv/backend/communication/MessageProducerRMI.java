package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.rmi.MessageProducer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageProducerRMI extends UnicastRemoteObject implements MessageProducer {
    private final MessageProducer messageProducerImpl;

    public MessageProducerRMI(MessageProducer messageProducer) throws RemoteException {
        super();
        this.messageProducerImpl = messageProducer;
    }

    @Override
    public void produce(String s, String s1, String s2) throws RemoteException {
        messageProducerImpl.produce(s,s1,s2);
    }
}
