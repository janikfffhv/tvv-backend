package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RolesTopicsImplTest {

    @Test
    void RolesTopicsKorrektErstellenUndMitInformationenBefuellen() throws RemoteException {
        System.out.println("-------------------------------------Test 1 RolesTopicsKorrektErstellenUndMitInformationenBefuellen-------------------------");

        RolesTopicsImpl rolesTopics = (RolesTopicsImpl) HibernateService.rolesTopicsImpl();

        String userID = "tf-test";

        //Topics angeben
        List<String> topics = new ArrayList<>();
        topics.add(Kategorie.KINO.getName());
        topics.add(Kategorie.THEATER.getName());
        topics.add(Kategorie.KONZERT.getName());

        rolesTopics.setTopics(topics, userID);

        List<String> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER.getName());
        rollen.add(Rolle.OPERATOR.getName());

        assertEquals(rollen, rolesTopics.getRoles(userID));
        assertEquals(topics, rolesTopics.getTopics(userID));
    }

}