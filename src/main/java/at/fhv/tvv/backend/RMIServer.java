package at.fhv.tvv.backend;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import javax.annotation.PostConstruct;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Startup
@Singleton
public class RMIServer {
    @PostConstruct
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            Naming.rebind("rmi://localhost/eventSearch", HibernateService.eventSearchRMI());
            Naming.rebind("rmi://localhost/customerSearch", HibernateService.customerSearchRMI());
            Naming.rebind("rmi://localhost/sessionFactory", HibernateService.tvvSessionFactoryRMI());
            Naming.rebind("rmi://localhost/verkauf", HibernateService.verkaufImplRMI());
            Naming.rebind("rmi://localhost/customerTickets", HibernateService.customerTicketsRMI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
