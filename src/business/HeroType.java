package business;

public enum HeroType {
    GUSTAVE("Gustave", "Spécialiste du feu et des coups puissants"),
    MAELLE("Maelle", "Maîtresse de la magie lunaire"),
    LUNE("Lune", "Discrète et rapide"),
    MONOCO("Monoco", "Polyvalent et imprévisible"),
    VESCO("Vesco","Combattant hors pair mystérieux et attachant");

    private final String nom;
    private final String description;

    HeroType(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
