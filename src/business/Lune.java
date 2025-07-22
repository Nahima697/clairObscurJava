package business;

public class Lune extends Hero {

    public Lune(String nom) {
        super(nom, 70, 15, 30, 50);
        this.ajouterPouvoir(new FoudreCinglante());
        this.ajouterPouvoir(new BouclierDeGivre());
        this.ajouterPouvoir(new GlypheEtourdissant());
        pouvoirs.add(new Esquive());
    }

}

