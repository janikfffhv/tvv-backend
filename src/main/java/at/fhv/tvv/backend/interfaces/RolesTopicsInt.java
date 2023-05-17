package at.fhv.tvv.backend.interfaces;

import javax.ejb.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

@Local
public interface RolesTopicsInt {
    List<String> getRoles(String userId);
    List<String> getTopics(String userId);
    void setTopics(List<String> topics, String userId);
}
