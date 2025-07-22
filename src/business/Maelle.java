package business;

public class Maelle extends Hero {
    public Maelle (String nom) {
        super(nom, 80, 18, 6, 20);
        pouvoirs.add(new FrappeLunaire());
        pouvoirs.add(new PasDOmbre());
        pouvoirs.add(new LameSpectrale());
        pouvoirs.add(new Esquive());
    }

}
