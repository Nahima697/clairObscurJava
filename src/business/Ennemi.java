package business;

import interfaces.PouvoirSpecial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Ennemi extends Personnage{
    protected List<PouvoirSpecial> pouvoirs;
    private final Random random = new Random();

    public Ennemi(String nom, int pv, int attaque, int defense) {
        super(nom, pv, attaque, defense);
        this.pouvoirs = new ArrayList<>();
    }

    public List<PouvoirSpecial> getPouvoirs() {
        return pouvoirs;
    }

    public void setPouvoirs(List<PouvoirSpecial> pouvoirs) {
        this.pouvoirs = pouvoirs;
    }
    /**
     * Ajoute un pouvoir spécial à la liste de l'ennemi.
     *
     * @param pouvoir Le pouvoir à ajouter
     */
    public void ajouterPouvoir(PouvoirSpecial pouvoir) {
        this.pouvoirs.add(pouvoir);
    }


    /**
     * L'ennemi utilise un pouvoir aléatoire sur une cible.
     */
    public void utiliserPouvoirAleatoire(Personnage cible) {
        if (pouvoirs.isEmpty()) {
            System.out.println(getNom() + " n'a aucun pouvoir spécial !");
            return;
        }

        int index = random.nextInt(pouvoirs.size());
        PouvoirSpecial pouvoir = pouvoirs.get(index);

        // Utilise le pouvoir avec l'ennemi comme lanceur et la cible donnée
        pouvoir.utiliserPouvoir(this, cible);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ennemi ennemi = (Ennemi) o;
        return Objects.equals(pouvoirs, ennemi.pouvoirs) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pouvoirs, random);
    }

    @Override
    public String toString() {
        return String.format("Ennemi: %s | PV: %d | ATK: %d | DEF: %d | Pouvoirs: %s",
                getNom(), getPv(), getAttaque(), getDefense(), pouvoirs.stream().map(Object::toString).toList());
    }
}
