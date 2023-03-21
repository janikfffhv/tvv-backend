package at.fhv.tvv.backend.domain.model.rolle;

import javax.persistence.*;

@Entity
public class Rolle {

    @Id
    @GeneratedValue
    private Long rolleInternalId;

    @Enumerated(EnumType.STRING)
    private Rollenbezeichnung rollenbezeichnung;

    protected Rolle() {

    }

    public Rolle(Rollenbezeichnung rollenbezeichnung) {
        this.rollenbezeichnung = rollenbezeichnung;
    }

    public Long getRolleInternalId() {
        return rolleInternalId;
    }

    public Rollenbezeichnung getRollenbezeichnung() {
        return rollenbezeichnung;
    }
}
