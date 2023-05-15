package at.fhv.tvv.backend.communication;

import at.fhv.tvv.shared.dto.CustomerSearchDTO;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchRMITest {

    @Test
    void wennNachMariaVornameGesuchtWirdSollenAlleMariasAlsListeZurueckgegebenWerden() throws RemoteException {

        CustomerSearchRMI customerSearchRMI = new CustomerSearchRMI();

            String suchbegriff = "Maria";

        List<CustomerSearchDTO> actualCustomers = customerSearchRMI.searchByString(suchbegriff);

        //Test gültig, wenn gilt...
        assertEquals(actualCustomers.get(0).getVorname(), "Maria");

    }

    @Test
    void wennNachSorgNachnameGesuchtWirdSollenAlleLeuteMitNachnamenSorgGefundenWerden() throws RemoteException {

        CustomerSearchRMI customerSearchRMI = new CustomerSearchRMI();

        String suchbegriff = "Sorg";

        List<CustomerSearchDTO> actualCustomers = customerSearchRMI.searchByString(suchbegriff);

        System.out.println("Vorname: " + actualCustomers.get(0).getVorname()
                + "\nNachname: " + actualCustomers.get(0).getNachname()
                + "\nUUID: " + actualCustomers.get(0).getCustomerId());

        //Test gültig, wenn gilt...
        assertEquals(actualCustomers.get(0).getNachname(), suchbegriff);

    }

    @Test
    void wennNachTeilstringVonAidenGesuchtSollenKeineKundenGefundenWerden() throws RemoteException {

        CustomerSearchRMI customerSearchRMI = new CustomerSearchRMI();

            String teilstring = "Aid";
            String normalstring = "Aiden";

        List<CustomerSearchDTO> actualCustomersTeil = customerSearchRMI.searchByString(teilstring);
        List<CustomerSearchDTO> actualCustomersNormal = customerSearchRMI.searchByString(normalstring);

        //Test gültig, wenn gilt...
        assertEquals(actualCustomersNormal.get(0).getVorname(), "Aiden");
        assertEquals(actualCustomersTeil.size(), 0);

    }

    @Test
    void wennNachNameKleinGeschriebenGesuchtWirdSollenKeineKundenGefundenWerden() throws RemoteException {

        CustomerSearchRMI customerSearchRMI = new CustomerSearchRMI();

        String kleinString = "maria";
        String normalString = "Maria";

        List<CustomerSearchDTO> actualCustomersKlein = customerSearchRMI.searchByString(kleinString);
        List<CustomerSearchDTO> actualCustomersNormal = customerSearchRMI.searchByString(normalString);

        //Test gültig, wenn gilt...
        assertEquals(actualCustomersKlein.size(), 0);
        assertEquals(actualCustomersNormal.get(0).getVorname(), normalString);
        assertEquals(actualCustomersNormal.get(1).getVorname(), normalString);
        assertEquals(actualCustomersNormal.get(2).getVorname(), normalString);

    }

    //-------------------------------------------------------------------

    @Test
    void wennNachBestimmterUUIDGesuchtWirdSollAidenSorgAlsKundeGefundenWerden() throws RemoteException {

        /* GESUCHTER KUNDE
        Vorname: Aiden
        Nachname: Sorg
        UUID: af3ce120-2059-4533-95d6-883f1567d376
         */

        CustomerSearchRMI customerSearchRMI = new CustomerSearchRMI();

         UUID sucheID = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");

        CustomerSearchDTO actualCustomer = customerSearchRMI.searchById(sucheID);

        //Test gültig, wenn gilt...
        assertEquals(actualCustomer.getCustomerId(), sucheID);
        assertEquals(actualCustomer.getVorname(), "Aiden");
        assertEquals(actualCustomer.getNachname(), "Sorg");

    }

}