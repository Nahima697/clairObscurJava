package business;

import interfaces.PouvoirSpecial;

public class PasDOmbre implements PouvoirSpecial {
    @Override
    public String getNom() {
        return "Pas d’Ombre";
    }

    @Override
    public int getCoutMana() {
        return 0;
    }

    @Override
    public void utiliserPouvoir(Personnage lanceur, Personnage cible) {
        if (lanceur instanceof PeintresseIllusoire peintresse) {
            System.out.println(peintresse.getNom() + " se fond dans l’ombre... il esquivera la prochaine attaque !");
            peintresse.setPeutEsquiver(true);
        } else if (lanceur instanceof Golgra golgra) {
            System.out.println(golgra.getNom() + " se fond dans l’ombre... il esquivera la prochaine attaque !");
            golgra.setPeutEsquiver(true);
        } else {
            System.out.println("Ce pouvoir ne peut être utilisé que par un ennemi.");
        }
    }
}
