package business;

import interfaces.Affichable;

import java.io.*;

/**
 * Classe abstraite représentant un personnage dans le jeu.
 * Un personnage a un nom, des points de vie (PV), une attaque et une défense.
 * Les classes Hero et Ennemi hériteront de cette classe.
 */
public  abstract class Personnage implements Affichable {
    private String nom;
    private int pv;
    private int attaque;
    private int defense;

/**
 * Constructeur de base pour un personnage.
 *
 * @param nom Nom du personnage
 * @param pv Points de vie
 * @param attaque Valeur d’attaque
 * @param defense Valeur de défense
 */
    public Personnage(String nom, int pv, int attaque, int defense) {
        this.nom = nom;
        this.pv = pv;
        this.attaque = attaque;
        this.defense = defense;
    }

/**
 * Attaque un autre personnage.
 *
 * @param cible Le personnage ciblé par l'attaque
 */
    public  void attaquer(Personnage cible) {
        int degats = this.attaque - cible.getDefense();
        if (degats > 0) {
            System.out.println(this.getNom() + " attaque " + cible.getNom() + " !");
            cible.prendreDegat(degats);
        }
    }
/**
 * Réduit les PV du personnage en fonction des dégâts reçus.
 *
 * @param degats Montant des dégâts à appliquer
 */
public void prendreDegat(int degats) {
    this.pv -= degats;
    System.out.println(getNom() + " subit " + degats + " dégâts. PV restants : " + getPv());
    if (pv <= 0) {
        System.out.println(getNom() + " est vaincu !");
    }
}

/**
 * Vérifie si le personnage est encore en vie.
 *
 * @return true si PV > 0, sinon false
 */
    public boolean estVivant() {
        return this.pv > 0;
    }
    /**
     * Afficher la fiche du personnage en ASCII Art en console
     */

    @Override
    public void afficherFiche() {
        System.out.println(getNom());
        System.out.println(getPv());
        System.out.println(getAttaque());
        System.out.println(getDefense());
        String chemin = "/resources/" + getNom() + ".txt";

        try (InputStream input = getClass().getResourceAsStream(chemin)) {
            if (input == null) {
                System.err.println("Fichier non trouvé : " + chemin);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/** Getter et Setter
 * */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
