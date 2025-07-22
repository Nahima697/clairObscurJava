package business;

public class Gustave extends Hero {

    public Gustave(String nom) {
        super(nom, 100, 22, 25, 30);
        this.ajouterPouvoir(new BouclierDeGivre());
        this.ajouterPouvoir(new FoudreCinglante());
        this.ajouterPouvoir(new Esquive());
    }

    @Override
    public void attaquer(Personnage cible) {
        int degats = getAttaque() - cible.getDefense();
        if (degats > 0) {
            cible.prendreDegat(degats);
        } else {
            System.out.println(getNom() + " n'a pas réussi à infliger des dégâts.");
        }
    }
}
