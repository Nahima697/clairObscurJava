package business;

import interfaces.PouvoirSpecial;
import utils.exception.ManaInsuffisantException;
import utils.exception.PotionIndisponibleException;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un héros jouable dans le jeu Clair Obscur.
 * Un héros possède des points de vie, d’attaque, de défense, du mana,
 * ainsi qu’une liste de pouvoirs spéciaux et une gestion de potion.
 */
public class Hero extends Personnage {

    private int mana;
    protected List<PouvoirSpecial> pouvoirs;
    protected boolean aUtilisePotion = false;
    protected boolean peutEsquiver = false;
    protected int xp = 1;

    /**
     * Construit un héros avec ses attributs de base.
     *
     * @param nom     Nom du héros
     * @param pv      Points de vie
     * @param attaque Points d’attaque
     * @param defense Points de défense
     * @param mana    Points de mana
     */
    public Hero(String nom, int pv, int attaque, int defense, int mana) {
        super(nom, pv, attaque, defense);
        this.mana = mana;
        this.pouvoirs = new ArrayList<>();
    }

    /**
     * Utilise un pouvoir spécial à l’index donné, si le mana est suffisant.
     *
     * @param index Index du pouvoir dans la liste
     * @param cible Personnage ciblé
     * @throws ManaInsuffisantException si le héros n’a pas assez de mana
     * @throws IllegalArgumentException si l’index est invalide
     */
    public void utiliserPouvoirSpecial(int index, Personnage cible) throws ManaInsuffisantException {
        if (index < 0 || index >= pouvoirs.size()) {
            throw new IllegalArgumentException("Pouvoir invalide");
        }

        PouvoirSpecial pouvoir = pouvoirs.get(index);
        if (mana < pouvoir.getCoutMana()) {
            throw new ManaInsuffisantException("Pas assez de mana pour utiliser " + pouvoir.getNom());
        }

        mana -= pouvoir.getCoutMana();
        pouvoir.utiliserPouvoir(this, cible);
    }

    /**
     * Utilise une potion (si disponible) et rend 20 PV.
     *
     * @throws PotionIndisponibleException si la potion a déjà été utilisée dans ce combat
     */
    public void utiliserPotion() throws PotionIndisponibleException {
        if (aUtilisePotion) {
            throw new PotionIndisponibleException("Vous n'avez plus de potion pour ce combat.");
        }
        setPv(getPv() + 20);
        aUtilisePotion = true;
    }

    public void ajouterNiveau () {
        setPv(getPv() + getXp());
        setAttaque(getAttaque()  + getXp());
        setDefense(getDefense() + getXp());
        setMana(getMana() + getXp());
    }

    /**
     * Ajoute un pouvoir spécial au héros.
     *
     * @param pouvoir Le pouvoir à ajouter
     */
    public void ajouterPouvoir(PouvoirSpecial pouvoir) {
        this.pouvoirs.add(pouvoir);
    }

    /**
     * Affiche les statistiques actuelles du héros.
     */
    public void afficherStats() {
        System.out.println(this.toString());
    }

    // ------------------ Gestion de l'esquive ------------------

    /**
     * Réception des dégâts, sauf si le héros peut esquiver.
     *
     * @param degats Points de dégâts à infliger
     */
    @Override
    public void prendreDegat(int degats) {
        if (peutEsquiver) {
            System.out.println(getNom() + " a esquivé l'attaque !");
            peutEsquiver = false;
        } else {
            super.prendreDegat(degats);
        }
    }

    // ------------------ Getters & Setters ------------------

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public List<PouvoirSpecial> getPouvoirs() {
        return pouvoirs;
    }

    public void setPouvoirs(List<PouvoirSpecial> pouvoirs) {
        this.pouvoirs = pouvoirs;
    }

    public boolean isaUtilisePotion() {
        return aUtilisePotion;
    }

    public void setaUtilisePotion(boolean aUtilisePotion) {
        this.aUtilisePotion = aUtilisePotion;
    }

    public boolean peutEsquiver() {
        return peutEsquiver;
    }

    public void setPeutEsquiver(boolean peutEsquiver) {
        this.peutEsquiver = peutEsquiver;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
// ------------------ Overrides ------------------

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * @return Représentation textuelle du héros avec ses stats de combat
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Héros: ").append(getNom())
                .append(" | PV: ").append(getPv())
                .append(" | ATK: ").append(getAttaque())
                .append(" | DEF: ").append(getDefense())
                .append(" | MANA: ").append(mana)
                .append(" | Potions utilisées: ").append(aUtilisePotion ? "Oui" : "Non");

        sb.append("\n🧙 Pouvoirs spéciaux : ");
        if (pouvoirs.isEmpty()) {
            sb.append("Aucun");
        } else {
            for (int i = 0; i < pouvoirs.size(); i++) {
                sb.append("\n  ").append(i + 1).append(". ").append(pouvoirs.get(i).getNom());
            }
        }
        return sb.toString();
    }

}
