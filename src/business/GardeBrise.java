package business;

public class GardeBrise extends Ennemi {
    public GardeBrise() {
        super("Garde Brisé", 60, 8, 12);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " vous frappe d’un coup de masse !");
        int degats = (getAttaque() * 2 / 3) - cible.getDefense();
        if (degats > 0) {
            cible.prendreDegat(degats);
        } else {
            System.out.println(cible.getNom() + " pare complètement l’attaque !");
        }
    }
}
