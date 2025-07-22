package business;

public class Monoco extends Hero {
    private boolean aTransforme = false;

    public Monoco(String nom) {
        super(nom, 100, 20, 8, 30);
        pouvoirs.add(new Esquive());
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
        else System.out.println(getNom() + " n'a pas réussi à infliger des dégâts.");
    }
}
