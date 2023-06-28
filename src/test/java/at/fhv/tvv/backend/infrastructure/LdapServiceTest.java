package at.fhv.tvv.backend.infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LdapServiceTest {

    @Test
    void wennUserJosieMitPasswortGesuchtWirdSollJosieStoeberInLDAPDirectoryGefundenWerden() {

        String expectedUserID = "de5cc747-d471-44a1-9b37-784c81e704e5"; // => UUID von User Josie Stöber

        //SUCHDATEN
        String username = "Josie";
        String passwort = "Josie";

        LdapService ldapService = new LdapService();

        //USER "Josie" SUCHEN IN LDAP DIRECTORY

        String actualUserID = ldapService.customerInLdap(username, passwort);

        assertEquals(expectedUserID, actualUserID);

    }

    @Test
    void wennUserAidenMitPasswortGesuchtWirdSollJosieStoeberInLDAPDirectoryGefundenWerden() {

        String expectedUserID = "af3ce120-2059-4533-95d6-883f1567d376"; // => UUID von User Aiden Sorg

        //SUCHDATEN
        String username = "Aiden";
        String passwort = "Aiden";

        LdapService ldapService = new LdapService();

        //USER "Aiden" SUCHEN IN LDAP DIRECTORY

        String actualUserID = ldapService.customerInLdap(username, passwort);

        assertEquals(expectedUserID, actualUserID);

    }

    @Test
    void wennNachUngueltigerUserPasswortKombinationGesuchtWirdSollFehlerAusgegebenWerden() {

        String expectedErgebnis = "FALSE"; // => FEHLER

        //SUCHDATEN
        String username = "Aiden";
        String passwort = "GibtEsNicht";

        LdapService ldapService = new LdapService();

        //MIT UNGUELTIGEN CREDENTIALS IN LDAP DIRECTORY SUCHEN

        String actualErgebnis = ldapService.customerInLdap(username, passwort);

        assertEquals(expectedErgebnis, actualErgebnis);

    }

}