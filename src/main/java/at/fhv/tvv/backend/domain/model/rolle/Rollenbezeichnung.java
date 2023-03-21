package at.fhv.tvv.backend.domain.model.rolle;

public enum Rollenbezeichnung {

    CHEF("CHEF"),
    MITARBEITER("MITARBEITER");

    private final String name;

    Rollenbezeichnung(String name) {this.name = name;}

    public String getName() {return name;}
}
