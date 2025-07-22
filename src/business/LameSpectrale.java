package business;

import interfaces.PouvoirSpecial;

public class LameSpectrale implements PouvoirSpecial {
    @Override
    public String getNom() { return "Lame Spectrale"; }

    @Override
    public int getCoutMana() { return 7; }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if ( lanceur instanceof Hero hero && hero.getMana() >= getCoutMana()) {
           hero.setMana(hero.getMana() - getCoutMana());
            int degats = hero.getAttaque() - (cible.getDefense() / 2); // ignore moitié
            System.out.println("Une lame spectrale perce la défense de " + cible.getNom());
            if (degats > 0) cible.prendreDegat(degats);
        } else {
            System.out.println("Pas assez de mana.");
        }
    }
}
