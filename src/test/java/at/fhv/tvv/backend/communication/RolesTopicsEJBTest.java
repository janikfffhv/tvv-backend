package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.shared.rmi.RolesTopics;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RolesTopicsEJBTest {

    @Test
    void RolesTopicsKorrektErstellenUndMitInformationenBefuellen() throws RemoteException {

        //Test-RolesTopics erstellen
        RolesTopicsEJB rolesTopicsEJB = new RolesTopicsEJB();

        //Test-User
        String userID = "tf-test";

        //Topics angeben
        List<String> topics = new ArrayList<>();
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO.getName());
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.THEATER.getName());
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KONZERT.getName());

        rolesTopicsEJB.setTopics(topics, userID);


        //Test gültig, wenn gilt...
        assertEquals(topics, rolesTopicsEJB.getTopics(userID));

        List<String> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER.getName());
        rollen.add(Rolle.OPERATOR.getName());
        assertEquals(rollen, rolesTopicsEJB.getRoles(userID));

    }


}