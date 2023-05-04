package at.fhv.tvv.shared.rmi;

import at.fhv.tvv.shared.dto.MessageDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MessageConsumer extends Remote {
    List<MessageDTO> getMessages(String userName) throws RemoteException;
}
