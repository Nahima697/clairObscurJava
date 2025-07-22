package main;

import business.*;
import interfaces.PouvoirSpecial;
import utils.exception.ManaInsuffisantException;
import utils.exception.PotionIndisponibleException;
import utils.factory.HeroFactory;
import utils.factory.EnnemiFactory;
import utils.service.ScoreService;

import java.util.Scanner;

public class GameManager {

    private final Scanner scanner = new Scanner(System.in);
    private Hero hero;
    private int ennemisVaincus = 0;

    public void lancerJeu() {
        System.out.println("=== Bienvenue dans Clair Obscur Expédition 40 RPG ===");

        // Choix du nom
        System.out.print("Entrez votre prénom : ");
        String nom = scanner.nextLine();

        // Choix du type de héros
        HeroType typeChoisi = null;
        while (typeChoisi == null) {
            System.out.println("Choisissez votre héros :");
            for (HeroType type : HeroType.values()) {
                System.out.println("- " + type.getNom() + " : " + type.getDescription());

            }

            System.out.print("Votre choix : ");
            String choix = scanner.nextLine().trim().toUpperCase();

            try {
                typeChoisi = HeroType.valueOf(choix);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Classe inconnue. Essayez encore.");
            }
        }

        hero = HeroFactory.createHero(typeChoisi, typeChoisi.getNom());

        System.out.println("Bienvenue, " + nom + " vous jouez en tant que " + hero.getNom() + " !");
        hero.afficherStats();

        ennemisVaincus = 0;

        // Boucle principale de combat
        while (hero.estVivant()) {
            System.out.println("\n--- Un nouvel ennemi approche ---");
            Ennemi ennemi = EnnemiFactory.genererAleatoire();
            System.out.println("Ennemi : " + ennemi.getNom() + " numéro " + ennemisVaincus + 1);

            lancerCombat(ennemi);

            if (!hero.estVivant()) {
                System.out.println("Vous êtes mort...");
                break;
            }

            ennemisVaincus++;
            hero.ajouterNiveau();
            hero.setaUtilisePotion(false);

            // Affichage de gain d'Xp après un combat
            System.out.println("Votre Xp est désormais de " + hero.getXp() + " !");
        }

        System.out.println("\nFin de partie. Ennemis vaincus : " + ennemisVaincus);
        ScoreService.sauvegarderScore(nom, hero.getNom(), ennemisVaincus);

        // Message de fin selon le score
        if (ennemisVaincus < 3) {
            System.out.println("🥉 Courage, jeune aventurier !");
        } else if (ennemisVaincus < 7) {
            System.out.println("🥈 Pas mal du tout !");
        } else {
            System.out.println("🥇 Héros légendaire !");
        }

        // Proposition de rejouer
        System.out.print("Souhaitez-vous rejouer ? (O/N) : ");
        String reponse = scanner.nextLine().trim().toUpperCase();
        if (reponse.equals("O")) {
            lancerJeu();
        } else {
            System.out.println("Merci d'avoir joué ! À bientôt.");
        }
    }

    private boolean afficherMenu(Ennemi ennemi) {
        System.out.println("\n🎮 Que voulez-vous faire ?");
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser un pouvoir");
        System.out.println("3. Boire une potion");
        System.out.println("4. Afficher les statistiques");

        String choix = scanner.nextLine();

        switch (choix) {
            case "1" -> {
                hero.attaquer(ennemi);
                return true;
            }
            case "2" -> {
                try {
                    utiliserPouvoir(ennemi);
                    return true;
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
                    return true;
                } catch (PotionIndisponibleException e) {
                    System.out.println("❌ " + e.getMessage());
                }
            }
            case "4" -> {
                hero.afficherStats();
            }
            default -> {
                System.out.println("❌ Choix invalide.");
            }
        }
        return false;
    }

    private void lancerCombat(Ennemi ennemi) {
        hero.afficherFiche();
        ennemi.afficherFiche();
        while (hero.estVivant() && ennemi.estVivant()) {
            boolean actionEffectuee = false;

            while (!actionEffectuee) {
                actionEffectuee = afficherMenu(ennemi);
            }

            // Si l'ennemi est encore en vie, il joue son tour
            if (ennemi.estVivant()) {
                if (Math.random() < 0.5 && ennemi.getPouvoirs() != null && !ennemi.getPouvoirs().isEmpty()) {
                    ennemi.utiliserPouvoirAleatoire(hero);
                } else {
                    ennemi.attaquer(hero);
                }
            }

            // Affichage des stats après le tour
            System.out.println("\n📊 État après ce tour :");
            hero.afficherStats();
            System.out.println(ennemi.getNom() + " - PV : " + ennemi.getPv());
        }

        if (!ennemi.estVivant()) {
            System.out.println("\n✅ " + ennemi.getNom() + " a été vaincu !");
        } else if (!hero.estVivant()) {
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
