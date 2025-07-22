package business;

import interfaces.PouvoirSpecial;

public class EncreDeBrume implements PouvoirSpecial {
    @Override
    public String getNom() { return "Encre de Brume"; }

    @Override
    public int getCoutMana() { return 0; }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if ( lanceur instanceof Ennemi ennemi ) {
            System.out.println(ennemi.getNom() + " enveloppe " + cible.getNom() + " dans une brume troublante !");
        } else {
            System.out.println("C'est pas un ennemi !");
        }
    }
}
