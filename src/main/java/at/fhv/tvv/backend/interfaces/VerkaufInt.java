package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.VerkaufDTO;
import javax.ejb.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;
@Local
public interface VerkaufInt {
    boolean kaufe(VerkaufDTO verkauf);
}
