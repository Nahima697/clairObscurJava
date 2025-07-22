package business;

public class PeintresseIllusoire extends Ennemi {
    private boolean peutEsquiver = false;
    private int esquivesRestantes = 3;
    public PeintresseIllusoire() {
        super("Peintresse Illusoire", 250, 25, 30);
        this.ajouterPouvoir(new LettreMaudite());
        this.ajouterPouvoir(new EncreDeBrume());
        this.ajouterPouvoir(new PasDOmbre());

    }
    @Override
    public void attaquer(Personnage cible) {
        System.out.println(getNom() + " tord la réalité, infligeant des illusions mentales !");
        cible.prendreDegat(10);
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
     * Surcharge la méthode prendreDegat pour tenir compte de l’esquive
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

