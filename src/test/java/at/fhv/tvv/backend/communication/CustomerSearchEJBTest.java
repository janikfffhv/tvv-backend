package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.CustomerSearchDTO;
import at.fhv.tvv.shared.rmi.CustomerSearch;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchEJBTest {

    @Test
    void wennNachMariaVornameGesuchtWirdSollenAlleMariasAlsListeZurueckgegebenWerden() {

        CustomerSearchEJB customerSearchEJB = new CustomerSearchEJB();

        String suchbegriff = "Maria";

        List<CustomerSearchDTO> actualCustomers = customerSearchEJB.searchByString(suchbegriff);

        for(CustomerSearchDTO customer : actualCustomers) {
            System.out.println("Customer No." + customer.getCustomerId() + " - " + customer.getVorname() + " " + customer.getNachname());
        }

        //Test gültig, wenn gilt...
        assertEquals(suchbegriff, actualCustomers.get(0).getVorname());

    }

    @Test
    void wennNachSorgNachnameGesuchtWirdSollenAlleLeuteMitNachnamenSorgGefundenWerden() {

        CustomerSearchEJB customerSearchEJB = new CustomerSearchEJB();

        String suchbegriff = "Sorg";

        List<CustomerSearchDTO> actualCustomers = customerSearchEJB.searchByString(suchbegriff);

        for(CustomerSearchDTO customer : actualCustomers) {
            System.out.println("Customer No." + customer.getCustomerId() + " - " + customer.getVorname() + " " + customer.getNachname());
        }

        //Test gültig, wenn gilt...
        assertEquals(suchbegriff, actualCustomers.get(0).getNachname());

    }

    @Test
    void wennNachTeilstringVonAidenGesuchtSollenKeineKundenGefundenWerden() {

        CustomerSearchEJB customerSearchEJB = new CustomerSearchEJB();

        String teilstring = "Aid";
        String normalstring = "Aiden";

        List<CustomerSearchDTO> actualCustomersTeil = customerSearchEJB.searchByString(teilstring);
        List<CustomerSearchDTO> actualCustomersNormal = customerSearchEJB.searchByString(normalstring);

        for(CustomerSearchDTO customer : actualCustomersNormal) {
            System.out.println("Customer No." + customer.getCustomerId() + " - " + customer.getVorname() + " " + customer.getNachname());
        }

        //Test gültig, wenn gilt...
        assertEquals(actualCustomersNormal.get(0).getVorname(), "Aiden");
        assertEquals(actualCustomersTeil.size(), 0);

    }

    @Test
    void wennNachNameKleinGeschriebenGesuchtWirdSollenKeineKundenGefundenWerden() {

        CustomerSearchEJB customerSearchEJB = new CustomerSearchEJB();

        String kleinString = "maria";
        String normalString = "Maria";

        List<CustomerSearchDTO> actualCustomersKlein = customerSearchEJB.searchByString(kleinString);
        List<CustomerSearchDTO> actualCustomersNormal = customerSearchEJB.searchByString(normalString);

        for(CustomerSearchDTO customer : actualCustomersNormal) {
            System.out.println("Customer No." + customer.getCustomerId() + " - " + customer.getVorname() + " " + customer.getNachname());
        }

        //Test gültig, wenn gilt...
        assertEquals(actualCustomersKlein.size(), 0);
        assertEquals(normalString, actualCustomersNormal.get(0).getVorname());
        assertEquals(normalString, actualCustomersNormal.get(1).getVorname());
        assertEquals(normalString, actualCustomersNormal.get(2).getVorname());

    }

    //-------------------------------------------------------------------

    @Test
    void wennNachBestimmterUUIDGesuchtWirdSollAidenSorgAlsKundeGefundenWerden() {

        /* GESUCHTER KUNDE
        Vorname: Aiden
        Nachname: Sorg
        UUID: af3ce120-2059-4533-95d6-883f1567d376
        */

        CustomerSearchEJB customerSearchEJB = new CustomerSearchEJB();

        UUID sucheID = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");

        CustomerSearchDTO actualCustomer = customerSearchEJB.searchById(sucheID);

        System.out.println("Customer No." + actualCustomer.getCustomerId() + " - " + actualCustomer.getVorname() + " " + actualCustomer.getNachname());

        //Test gültig, wenn gilt...
        assertEquals(sucheID, actualCustomer.getCustomerId());
        assertEquals("Aiden", actualCustomer.getVorname());
        assertEquals("Sorg", actualCustomer.getNachname());

    }

    @Test
    void wennUUIDGleichNULLDannSollExceptionGeworfenWerden() {

       //CustomerSearchEJB customerSearchEJB = new CustomerSearchEJB();

        //customerSearchEJB.searchById(null);

        //Test gültig, wenn gilt...
        //assertThrows(IllegalArgumentException.class,
                //()-> customerSearchEJB.searchById(null));

    }

}