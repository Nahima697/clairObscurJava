package business;

public class Golgra extends Ennemi {
    private boolean peutEsquiver = false;
    private int esquivesRestantes = 2;
    public Golgra() {
        super("Golgra", 70, 25, 10);
        this.ajouterPouvoir(new GriffeDeLOmbre());
        this.ajouterPouvoir(new PasDOmbre());
    }

    @Override
    public void attaquer(Personnage cible) {
        int degats = (getAttaque() + 5) - cible.getDefense();
        if (degats > 0) cible.prendreDegat(degats);
        System.out.println(getNom() + " exécute un combo brutal !");
    }

    public boolean isPeutEsquiver() {
        return peutEsquiver && esquivesRestantes > 0;
    }

    public void setPeutEsquiver(boolean peutEsquiver) {
        if (esquivesRestantes > 0) {
            this.peutEsquiver = peutEsquiver;
        }
    }

    public void utiliserEsquive() {
        if (peutEsquiver && esquivesRestantes > 0) {
            esquivesRestantes--;
            if (esquivesRestantes == 0) {
                peutEsquiver = false;
            }
        }
    }
    /**
     * Surcharge la méthode attaquer pour tenir compte de l’esquive
     */
    @Override
    public void prendreDegat(int degats) {
        if (peutEsquiver) {
            System.out.println(getNom() + " a esquivé l'attaque !");
            peutEsquiver = false;
        } else {
            super.prendreDegat(degats);
        }
    }
}
