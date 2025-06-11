package utils;

import business.*;

import java.util.Random;

public class EnnemiFactory {
    private EnnemiFactory() {}
    public static Ennemi genererAleatoire() {
        int rand = new Random().nextInt(6);
        return switch (rand) {
            case 0 -> new SoldatGestral();
            case 1 -> new Charognard();
            case 2 -> new GardeBrise();
            case 3 -> new Golgra();
            case 4  -> new AxonDeFeu();
            case 5 -> new PeintresseIllusoire();
            default -> new SqueletteDechu();
        };
    }
}
