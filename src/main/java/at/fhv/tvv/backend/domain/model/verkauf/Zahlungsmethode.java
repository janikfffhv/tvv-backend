package at.fhv.tvv.backend.domain.model.verkauf;

public enum Zahlungsmethode {

    BAR("BAR"),
    KREDITKARTE("KREDITKARTE"),
    RECHNUNG("RECHNUNG");

    private final String name;

    Zahlungsmethode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
