package business;

public class SoldatGestral extends Ennemi {
    public SoldatGestral() {
        super("Soldat Gestral", 50, 10, 5);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " frappe avec sa lame courte !");
        int degats = getAttaque() - cible.getDefense();
        if (degats > 0) cible.prendreDegat(degats);
    }
}
