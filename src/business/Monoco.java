package business;

public class Monoco extends Ennemi {
    private boolean aTransforme = false;

    public Monoco() {
        super("Monoco", 100, 20, 8);
    }

    @Override
    public void attaquer(Personnage cible) {
        if (!aTransforme && getPv() <= 50) {
            System.out.println(getNom() + " adopte une forme monstrueuse !");
            setAttaque(getAttaque() + 10);
            setDefense(getDefense() + 5);
            aTransforme = true;
        }
        int degats = getAttaque() - cible.getDefense();
        if (degats > 0) cible.prendreDegat(degats);
    }
}
