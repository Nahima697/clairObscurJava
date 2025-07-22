package utils.service;

import java.io.FileWriter;
import java.io.IOException;

public class ScoreService {
    public static void sauvegarderScore(String nom, String heroNom, int score) {
        try (FileWriter fw = new FileWriter("scores.txt", true)) {
            fw.write(nom + " \n Héro : " + heroNom + "\n - Ennemis vaincus : " + score + "\n");
            System.out.println("Score sauvegardé !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du score.");
        }
    }
}
