package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.rmi.MessageProducer;
import at.fhv.tvv.shared.rmi.RolesTopics;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RolesTopicsRMI extends UnicastRemoteObject implements RolesTopics {

    private final RolesTopics rolesTopicsImpl;

    public RolesTopicsRMI(RolesTopics rolesTopics) throws RemoteException {
        super();
        this.rolesTopicsImpl = rolesTopics;
    }

    @Override
    public List<String> getRoles(String s) throws RemoteException {
        return rolesTopicsImpl.getRoles(s);
    }

    @Override
    public List<String> getTopics(String s) throws RemoteException {
        return rolesTopicsImpl.getTopics(s);
    }

    @Override
    public void setTopics(List<String> list, String username) throws RemoteException {
        rolesTopicsImpl.setTopics(list, username);
    }
}
