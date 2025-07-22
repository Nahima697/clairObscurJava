package business;

public class Verso extends Hero {

    public Verso(String nom) {
        super(nom, 100, 15, 20, 40);
        this.ajouterPouvoir(new FoudreCinglante());
        this.ajouterPouvoir(new FrappeLunaire());
    }
}