package business;

import interfaces.PouvoirSpecial;

public class LettreMaudite implements PouvoirSpecial {
    private final int degats = 25;

    @Override
    public String getNom() {
        return "Lettre Maudite";
    }

    @Override
    public int getCoutMana() {
        return 0;
    }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if (lanceur instanceof Ennemi ennemi) {
            System.out.println(ennemi.getNom() + " lance sa Lettre Maudite sur " + cible.getNom() + "...");
            cible.prendreDegat(degats);
            System.out.println(cible.getNom() + " perd " + degats + " points de vie.");
        } else {
            System.out.println("Ce pouvoir ne peut être utilisé que par un ennemi.");
        }
    }
}
