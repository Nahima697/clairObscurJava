package business;

import interfaces.PouvoirSpecial;

public class BouleDeFeu implements PouvoirSpecial {
    @Override
    public String getNom() { return "Boule de Feu"; }

    @Override
    public int getCoutMana() { return 0; }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if ( lanceur instanceof Ennemi ennemi ) {
            System.out.println("Une boule de feu est lancée sur  " + cible.getNom() + "...");
            cible.prendreDegat(15);
        } else {
            System.out.println("Ce n'est pas un ennemi");
        }
    }
}
