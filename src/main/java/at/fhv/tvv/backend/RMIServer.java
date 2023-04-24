package at.fhv.tvv.backend;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Startup
@Singleton
public class RMIServer {
    @PostConstruct
    public void init() {
    }
}
