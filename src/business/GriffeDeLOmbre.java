package business;

import interfaces.PouvoirSpecial;

public class GriffeDeLOmbre implements PouvoirSpecial {

    @Override
    public String getNom() {
        return "Griffe de l’Ombre";
    }

    @Override
    public int getCoutMana() {
        return 0;
    }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if (lanceur instanceof Ennemi ennemi) {
            System.out.println(ennemi.getNom() + " frappe " + cible.getNom() + " avec sa Griffe de l’Ombre !");
                     int degats = (ennemi.getAttaque() * 2) - cible.getDefense();

            if (degats > 0) {
                cible.prendreDegat(degats);
                System.out.println(cible.getNom() + " subit " + degats + " points de dégâts.");
            } else {
                System.out.println(cible.getNom() + " encaisse l’attaque sans dommage.");
            }
        } else {
            System.out.println("Ce pouvoir ne peut être utilisé que par un ennemi.");
        }
    }
}
