package business;

public class GardeBrisé extends Ennemi {
    public GardeBrisé() {
        super("Garde Brisé", 60, 8, 12);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " vous frappe d’un coup de masse !");
        int degats = (getAttaque() * 2 / 3) - cible.getDefense(); // frappe plus lente
        if (degats > 0) cible.prendreDegat(degats);
    }
}
