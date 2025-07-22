package business;

import interfaces.PouvoirSpecial;

public class FrappeLunaire implements PouvoirSpecial {
    @Override
    public String getNom() { return "Frappe Lunaire"; }

    @Override
    public int getCoutMana() { return 5; }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if ( lanceur instanceof Hero hero && hero.getMana() >= getCoutMana()) {
            hero.setMana(hero.getMana() - getCoutMana());
            int degats = (lanceur.getAttaque() * 2) - cible.getDefense();
            System.out.println(lanceur.getNom() + " déclenche une frappe éclair !");
            if (degats > 0) cible.prendreDegat(degats);
        } else {
            System.out.println("Pas assez de mana.");
        }
    }
}
