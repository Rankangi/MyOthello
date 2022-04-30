package Model;

import javax.swing.*;

public class CaseOthello {

    private ImageIcon image;

    private Color couleur;

    CaseOthello(Color couleur) {
        this.couleur = couleur;
        switch (couleur) {
            case NOIR:
                this.image = new ImageIcon("Image/png-clipart-sobel-operator-circle-round-miscellaneous-white.png");
                break;
            case BLANC:
                this.image = new ImageIcon("Image/png-clipart-sobel-operator-circle-round-miscellaneous-white2.png");
                break;
        }
    }

    CaseOthello(){
        this.couleur = null;
        this.image = new ImageIcon("Image/case-blanche.jpg");
    }

    public ImageIcon getImage() {
        return image;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return String.valueOf(this.couleur);
    }
}
