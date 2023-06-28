package at.fhv.tvv.backend.domain.model.angestellte;

import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;

import javax.persistence.*;
import java.util.List;

@Entity
public class Angestellte {

    @Id
    @GeneratedValue
    private Long angestellteInternalId;

    private String benutzername;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Rolle> rollen;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Kategorie> topics;

    protected Angestellte() {

    }

    public Angestellte(String benutzername, List<Rolle> rollen, List<Kategorie> topics) {
        this.benutzername = benutzername;
        this.rollen = rollen;
        this.topics = topics;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public List<Rolle> getRollen() {
        return rollen;
    }

    public List<Kategorie> getTopics() {
        return topics;
    }

    public void setTopics(List<Kategorie> topics) {
        this.topics = topics;
    }
}
