package Model;

public class Joueur {
    protected Color couleur;

    protected boolean isHuman;


    public Joueur(Color couleur){
        this.isHuman = true;
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public boolean isHuman() {
        return !isHuman;
    }

    public void next() {
        couleur = couleur.next();
    }
}
