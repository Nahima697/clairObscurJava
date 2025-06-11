package business;

public class PeintresseIllusoire extends Ennemi {
    public PeintresseIllusoire() {
        super("Peintresse Illusoire", 90, 15, 12);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " tord la réalité, infligeant des illusions mentales !");
        cible.prendreDegat(10); // attaque magique fixe
    }
}
