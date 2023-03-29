package at.fhv.tvv.backend;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            Naming.rebind("rmi://localhost/eventSearch", HibernateService.eventSearchRMI());
            Naming.rebind("rmi://localhost/customerSearch", HibernateService.customerSearchRMI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
