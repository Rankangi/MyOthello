package Model;

public class Joueur {

    private Color couleur = Color.BLANC;

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void next() {
        couleur = couleur.next();
    }
}
