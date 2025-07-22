package utils.factory;

import business.*;

import java.util.List;

public class HeroFactory {

    private HeroFactory() {}

    public static Hero createHero(HeroType type, String nomJoueur) {
        return switch (type) {
            case GUSTAVE -> new Gustave(nomJoueur);
            case MAELLE -> new Maelle(nomJoueur);
            case LUNE   -> new Lune(nomJoueur);
            case VESCO  -> new Verso(nomJoueur);
            case MONOCO  -> new Monoco(nomJoueur);
        };
    }

}
