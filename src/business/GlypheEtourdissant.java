package business;

import interfaces.PouvoirSpecial;

public class GlypheEtourdissant implements PouvoirSpecial {
    public String getNom() { return "Glyphe Étourdissant"; }
    public int getCoutMana() { return 6; }

    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if (lanceur instanceof Hero hero && hero.getMana() >= getCoutMana()) {
            hero.setMana(hero.getMana() - getCoutMana());
            cible.prendreDegat(10);
            System.out.println(cible.getNom() + " est étourdi !");
        }
    }
}
