package at.fhv.tvv.backend;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class RMIServer {
    @PostConstruct
    public void init() {
    }
}
