package at.fhv.tvv.backend.domain.model.angestellte;

public enum Rolle {
    MITARBEITER("Mitarbeiter"),
    OPERATOR("Operator");

    private final String name;

    Rolle(String name) {this.name = name;}

    public String getName() {return name;}

}
