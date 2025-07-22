package business;

import interfaces.PouvoirSpecial;

public class BouclierDeGivre implements PouvoirSpecial {
    /**
     * @return
     */
    @Override
    public String getNom() {
        return "Bouclier de Givre";
    }

    /**
     * @return
     */
    @Override
    public int getCoutMana() {
        return 5;
    }
    /**
     * @param lanceur
     * @param cible
     */
    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if(lanceur instanceof Hero hero && hero.getMana()>= getCoutMana()) {
            hero.setDefense(hero.getDefense()+20);
            hero.setMana(hero.getMana() - getCoutMana());
        }
    }
}
