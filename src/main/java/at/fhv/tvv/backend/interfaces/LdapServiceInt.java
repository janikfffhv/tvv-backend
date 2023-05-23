package at.fhv.tvv.backend.interfaces;

import javax.ejb.Local;

@Local
public interface LdapServiceInt {

    String customerInLdap(String username, String password);

}
