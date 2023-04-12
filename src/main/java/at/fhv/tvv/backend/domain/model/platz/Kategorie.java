package at.fhv.tvv.backend.domain.model.platz;

public enum Kategorie {

    SITZPLATZ("SITZPLATZ"),
    STEHPLATZ("STEHPLATZ"),
    VIP("VIP"),
    PLATZ("PLATZ");

    private final String name;

    Kategorie(String name) {this.name = name;}

    public String getName() {return name;}

}
