package business;

public class SqueletteDechu extends Ennemi {
    public SqueletteDechu() {
        super("Squelette Déchu", 45, 9, 6);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " vous tranche avec une lame rouillée !");
        int degats = getAttaque() - cible.getDefense();
        if (degats > 0) cible.prendreDegat(degats);
    }
}
