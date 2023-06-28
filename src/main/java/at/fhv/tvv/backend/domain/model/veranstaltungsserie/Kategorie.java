package at.fhv.tvv.backend.domain.model.veranstaltungsserie;

public enum Kategorie {

    KINO("KINO"),
    KONZERT("KONZERT"),
    THEATER("THEATER");

    private final String name;

    Kategorie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
