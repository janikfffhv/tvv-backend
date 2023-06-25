package at.fhv.tvv.backend;
import at.fhv.tvv.shared.rmi.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class HibernateService {

    private static EntityManager entityManager;

    private static CustomerSearch customerSearchRMI;
    private static ActiveMQConnectionFactory activeMQConnectionFactory;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("Factory").createEntityManager();

        }
        return entityManager;
    }

    public static ActiveMQConnectionFactory activeMQConnectionFactory() {
        if(activeMQConnectionFactory == null) {
            activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://tvv-activemq:61616?jms.prefetchPolicy.all=0");
        }
        return activeMQConnectionFactory;
    }




    /**public static CustomerSearch customerSearchRMI() throws RemoteException {
        if(customerSearchRMI == null) {
            customerSearchRMI = new CustomerSearchEJB();
        }
        return customerSearchRMI;
    }**/


}
