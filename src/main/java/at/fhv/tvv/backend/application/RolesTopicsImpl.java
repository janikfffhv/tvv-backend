package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.interfaces.RolesTopicsInt;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class RolesTopicsImpl implements RolesTopicsInt {

    @EJB
    private EventRepository eventRepository;

    public RolesTopicsImpl() {

    }

    public RolesTopicsImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<String> getRoles(String s) {
        List<String> roles = new ArrayList<>();
        Optional<Angestellte> angestellter = eventRepository.getAngestellerById(s);
        if(angestellter.isPresent()) {
        for(Rolle rolle:angestellter.get().getRollen()) {
            roles.add(rolle.getName());
        }

    }
        return roles;
    }

    @Override
    public List<String> getTopics(String username) {
            List<String> topics = new ArrayList<>();
            Optional<Angestellte> angestellter2 = eventRepository.getAngestellerById(username);
            if(angestellter2.isPresent()) {
                for (Kategorie topic : angestellter2.get().getTopics()) {
                    topics.add(topic.getName());
                }
            }
                return topics;
    }

    @Override
    public void setTopics(List<String> list, String username) {
        List<Kategorie> topics = new ArrayList<>();
        for(String topic:list) {
            topics.add(Kategorie.valueOf(topic.toUpperCase()));
        }
        Optional<Angestellte> angestellter = eventRepository.getAngestellerById(username);
        if(angestellter.isPresent()) {
            Angestellte angestellter2 = angestellter.get();
            angestellter2.setTopics(topics);
            eventRepository.updateAngestellter(angestellter2);
        }
    }
}
