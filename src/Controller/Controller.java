package Controller;

import Model.Joueur;
import Model.Plateau;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Joueur joueur = new Joueur();
    private Plateau plateau = new Plateau();
    private View view = new View(this);

    @Override
    public void actionPerformed(ActionEvent e) {
        String res=e.getActionCommand(); //Récupérer la valeur du action listener
        String[] coordonnees = res.split("-"); //Séparer le string en deux parties
        int indiceligne = Integer.parseInt(coordonnees[0]);
        int indicecolonne = Integer.parseInt(coordonnees[1]);
        boolean deplacementCorrect = plateau.verifierdeplacement(indiceligne, indicecolonne, joueur.getCouleur(), true);
        if (deplacementCorrect){
            view.show(plateau.getGrille());
            boolean isfinish = plateau.next(joueur);
            System.out.println(isfinish);
            System.out.println(joueur.getCouleur());

        }
    }

    public Controller(){
        view.show(plateau.getGrille());
    }

}
