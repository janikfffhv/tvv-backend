package at.fhv.tvv.backend.domain.model.platz;

public enum Kategorie {

    SITZPLATZ("SITZPLATZ"),
    STEHPLATZ("STEHPLATZ"),
    VIP("VIP");

    private final String name;

    Kategorie(String name) {this.name = name;}

    public String getName() {return name;}

}
