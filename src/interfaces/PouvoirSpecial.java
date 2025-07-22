package interfaces;

import business.Personnage;

public interface PouvoirSpecial {
    String getNom();
    int getCoutMana();
    void utiliserPouvoir(Personnage lanceur, Personnage cible);
}
