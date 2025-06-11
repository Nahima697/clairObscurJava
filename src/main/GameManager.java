package main;

import business.*;
import interfaces.PouvoirSpecial;
import utils.exception.ManaInsuffisantException;
import utils.exception.PotionIndisponibleException;
import utils.factory.HeroFactory;
import utils.factory.EnnemiFactory;
import utils.service.ScoreService;

import java.util.Scanner;

public class GameFacade {

    private final Scanner scanner = new Scanner(System.in);
    private Hero hero;
    private int ennemisVaincus = 0;

    public void lancerJeu() {
        System.out.println("=== Bienvenue dans Clair Obscur RPG ===");

        // Choix du nom
        System.out.print("Entrez votre prénom : ");
        String nom = scanner.nextLine();

        // Choix du type de héros
        System.out.println("Choisissez votre héros :");
        for (HeroType type : HeroType.values()) {
            System.out.println("- " + type.getNom() + " : " + type.getDescription());
        }

        System.out.print("Votre choix : ");
        String choix = scanner.nextLine().trim().toUpperCase();

        try {
            HeroType typeChoisi = HeroType.valueOf(choix);
            hero = HeroFactory.createHero(typeChoisi, typeChoisi.getNom());
        } catch (IllegalArgumentException e) {
            System.out.println("Classe inconnue.");
            return;
        }

        System.out.println("Bienvenue, " + hero.getNom() + " !");
        hero.afficherStats();

        // Boucle principale de combat
        while (hero.estVivant()) {
            System.out.println("\n--- Un nouvel ennemi approche ---");
            Ennemi ennemi = EnnemiFactory.genererAleatoire();
            System.out.println("Ennemi : " + ennemi.getNom());

            lancerCombat(ennemi);

            if (!hero.estVivant()) {
                System.out.println("Vous êtes mort...");
                break;
            }

            ennemisVaincus++;
            hero.setaUtilisePotion(false);
        }

        System.out.println("\nFin de partie. Ennemis vaincus : " + ennemisVaincus);
        ScoreService.sauvegarderScore(hero.getNom(), ennemisVaincus);
    }

    private void lancerCombat(Ennemi ennemi)  {
        int compteur = 0;
        System.out.println("\n⚔️  Combat contre " + ennemi.getNom() + " commence !");
        hero.afficherFiche();

        while (hero.estVivant() && ennemi.estVivant()) {
            compteur++;
            System.out.println("\n" + hero.getNom() + " (PV: " + hero.getPv() + ", Mana: " + hero.getMana() + ") "
                    + "VS " + ennemi.getNom() + "numéro "  + compteur +" (PV: " + ennemi.getPv() + ")");

            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser un sort");
            System.out.println("3. Utiliser une potion");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1" -> hero.attaquer(ennemi);

                case "2" -> {
                    try {
                        utiliserPouvoir(ennemi);
                    } catch (ManaInsuffisantException e) {
                        System.out.println("❌ " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Pouvoir invalide.");
                    }
                }

                case "3" -> {
                    try {
                        hero.utiliserPotion();
                        System.out.println("🧪 Vous avez bu une potion. PV : " + hero.getPv());
                    } catch (PotionIndisponibleException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }

                default -> System.out.println("❌ Choix invalide.");
            }

            // Si l’ennemi est encore vivant, il attaque
            if (ennemi.estVivant()) {
                if (Math.random() < 0.5 && ennemi.getPouvoirs() != null && !ennemi.getPouvoirs().isEmpty()) {
                    ennemi.utiliserPouvoirAleatoire(hero);
                } else {
                    ennemi.attaquer(hero);
                }
            }

            // Affichage de l’état après le tour
            System.out.println("\n📊 État après ce tour :");
            hero.afficherStats();
            hero.setXp(hero.getXp() + 1);
            System.out.println("Votre Xp est désormais de " + hero.getXp() + " !");
            System.out.println(ennemi.getNom() + " - PV : " + ennemi.getPv());
            compteur ++;
        }

        // Fin du combat
        if (!ennemi.estVivant()) {
            System.out.println("\n✅ " + ennemi.getNom() + " a été vaincu !");
        } else {
            System.out.println("\n💀 " + hero.getNom() + " est tombé au combat...");
        }
    }


    private void utiliserPouvoir(Ennemi ennemi) throws ManaInsuffisantException {
        if (hero.getMana() <= 0) {
            System.out.println("Vous n'avez plus de Mana pour utiliser un sort.");
            return;
        }

        System.out.println("Sorts disponibles :");
        var pouvoirs = hero.getPouvoirs();
        for (int i = 0; i < pouvoirs.size(); i++) {
            PouvoirSpecial p = pouvoirs.get(i);
            System.out.println((i + 1) + ". " + p.getNom() + " (Mana : " + p.getCoutMana() + ")");
        }

        System.out.print("Choix du sort : ");
        try {
            int choix = Integer.parseInt(scanner.nextLine()) - 1;
            hero.utiliserPouvoirSpecial(choix, ennemi);
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide : veuillez entrer un nombre.");
        } catch (ManaInsuffisantException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Pouvoir invalide.");
        }
    }
}
