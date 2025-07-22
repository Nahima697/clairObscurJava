package business;

import interfaces.PouvoirSpecial;

public class FoudreCinglante implements PouvoirSpecial {
    /**
     * @return
     */
    @Override
    public String getNom() {
        return "Foudre Cinglante";
    }

    /**
     * @return
     */
    @Override
    public int getCoutMana() {
        return 6;
    }

    /**
     * @param lanceur
     * @param cible
     */
    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if (lanceur instanceof Hero hero && hero.getMana() >= getCoutMana()) {
            int degats = 30 - cible.getDefense();
            if (degats > 0) cible.prendreDegat(degats);
            hero.setMana(hero.getMana() - getCoutMana());
        }
    }
}
