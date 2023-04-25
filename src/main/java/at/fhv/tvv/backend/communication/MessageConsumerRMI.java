package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.application.MessageConsumerImpl;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.rmi.MessageConsumer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MessageConsumerRMI extends UnicastRemoteObject implements MessageConsumer {
    private final MessageConsumer messageConsumerImpl;

    public MessageConsumerRMI(MessageConsumer messageConsumerImpl) throws RemoteException {
        super();
        this.messageConsumerImpl = messageConsumerImpl;
    }

    @Override
    public List<MessageDTO> getMessages(String s) throws RemoteException {
        return messageConsumerImpl.getMessages(s);
    }
}
