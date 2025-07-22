package business;

public class AxonDeFeu extends Ennemi {
    public AxonDeFeu() {
        super("Axon de Feu", 80, 22, 5);
        this.ajouterPouvoir(new BouleDeFeu());
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " invoque une flamme ardente !");
        cible.prendreDegat(15);
    }
}
