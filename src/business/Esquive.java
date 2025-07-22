package business;

import interfaces.PouvoirSpecial;

public class Esquive implements PouvoirSpecial {

    @Override
    public String getNom() {
        return "Esquive";
    }

    @Override
    public int getCoutMana() {
        return 10;
    }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if (lanceur instanceof Hero hero) {
            if (hero.getMana() < getCoutMana()) {
                System.out.println("Pas assez de mana pour utiliser Esquive !");
                return;
            }
            hero.setMana(hero.getMana() - getCoutMana());
            System.out.println(hero.getNom() + " prépare une esquive pour le prochain tour !");
            hero.setPeutEsquiver(true);
        } else {
            System.out.println("Ce sort ne peut être utilisé que par un héros !");
        }
    }
}
