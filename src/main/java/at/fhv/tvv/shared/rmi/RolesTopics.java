package at.fhv.tvv.shared.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RolesTopics extends Remote {
    List<String> getRoles(String userId) throws RemoteException;
    List<String> getTopics(String userId) throws RemoteException;
    void setTopics(List<String> topics, String userId) throws RemoteException;
}
