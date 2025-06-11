package utils;

import business.*;

public class HeroFactory {
    private HeroFactory() {}

    public static Hero createHero(String classe, String nomJoueur) {
        return switch (classe.toLowerCase()) {
            case "gustave" -> new Gustave(nomJoueur);
            case "maelle"  -> new Maelle(nomJoueur);
            case "lune"    -> new Lune(nomJoueur);
            case "verso"   -> new Verso(nomJoueur);
            case "monoco"  -> new Monoco(nomJoueur);
            default        -> null;
        };
    }
}
