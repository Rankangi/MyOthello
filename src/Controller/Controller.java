package Controller;

import Model.Agent;
import Model.Color;
import Model.Joueur;
import Model.Plateau;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Controller implements ActionListener {

    private final Joueur joueur1 = new Joueur(Color.NOIR);
    private final Joueur joueur2 = new Agent(Color.BLANC);
    private final Joueur[] joueurs = {joueur1, joueur2};
    private int tour = 0;
    private final Plateau plateau = new Plateau();
    private final View view = new View(this);

    @Override
    public void actionPerformed(ActionEvent e) {
        if(joueurs[tour % 2].isHuman()){
            return;
        }
        String res=e.getActionCommand(); //Récupérer la valeur du action listener
        String[] coordonnees = res.split("-"); //Séparer le string en deux parties
        int indiceligne = Integer.parseInt(coordonnees[0]);
        int indicecolonne = Integer.parseInt(coordonnees[1]);
        boolean deplacementCorrect = plateau.verifierdeplacement(indiceligne, indicecolonne, joueurs[tour % 2].getCouleur(), true);
        if (deplacementCorrect){
            view.show(plateau.getGrille());
            boolean isfinish = plateau.isfinished(joueurs[tour % 2]);
            if (isfinish){
                System.out.println(Arrays.toString(plateau.score()));
            }
            tour++;
            if(joueurs[tour % 2].isHuman()){
                Agent joueur = (Agent) joueurs[tour % 2];
                int [] pos = joueur.play(plateau);
                System.out.println(Arrays.toString(pos));
                plateau.verifierdeplacement(pos[0], pos[1], joueur.getCouleur(), true);
                view.show(plateau.getGrille());
                isfinish = plateau.isfinished(joueur);
                if (isfinish){
                    System.out.println(Arrays.toString(plateau.score()));
                }
                tour++;
            }
        }
    }

    public Controller(){
        view.show(plateau.getGrille());
    }

}
