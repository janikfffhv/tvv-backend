package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.interfaces.RolesTopicsInt;
import at.fhv.tvv.shared.ejb.RolesTopics;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RolesTopicsEJB implements RolesTopics {

    @EJB
    private RolesTopicsInt rolesTopics;
    @Override
    public List<String> getRoles(String userId) {
        return rolesTopics.getRoles(userId);
    }

    @Override
    public List<String> getTopics(String userId) {
        return rolesTopics.getTopics(userId);
    }

    @Override
    public void setTopics(List<String> topics, String userId) {
        rolesTopics.setTopics(topics, userId);
    }
}
