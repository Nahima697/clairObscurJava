package business;

public class Lune extends Hero {

    public Lune(String nom) {
        super(nom, 60, 15, 10, 40);
        this.ajouterPouvoir(new FoudreCinglante());
        this.ajouterPouvoir(new BouclierDeGivre());
        this.ajouterPouvoir(new GlypheEtourdissant());
    }

    @Override
    public String toString() {
        return "Lune : " + getNom() + " | PV: " + getPv() +
                " | ATK: " + getAttaque() + " | DEF: " + getDefense() +
                " | MANA: " + getMana();
    }
}

