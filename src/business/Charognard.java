package business;

public class Charognard extends Ennemi {
    public Charognard() {
        super("Charognard", 40, 14, 2);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " griffe violemment !");
        int degats = getAttaque() - cible.getDefense();
        if (degats > 0) cible.prendreDegat(degats);
    }
}
