package business;

public class RenoirFugitif extends Ennemi {
    public RenoirFugitif() {
        super("Renoir", 70, 18, 6);
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " ralentit le temps avant de frapper !");
        int degats = (int) ((getAttaque() * 1.5) - cible.getDefense());
        if (degats > 0) cible.prendreDegat(degats);
    }
}
