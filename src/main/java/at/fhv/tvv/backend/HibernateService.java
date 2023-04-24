package at.fhv.tvv.backend;
import at.fhv.tvv.backend.application.CustomerSearchTicketsImpl;
import at.fhv.tvv.backend.communication.*;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.shared.rmi.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class HibernateService {

    private static EntityManager entityManager;

    private static CustomerSearch customerSearchRMI;


    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("Factory").createEntityManager();

        }
        return entityManager;
    }



    /**public static CustomerSearch customerSearchRMI() throws RemoteException {
        if(customerSearchRMI == null) {
            customerSearchRMI = new CustomerSearchEJB();
        }
        return customerSearchRMI;
    }**/


}
