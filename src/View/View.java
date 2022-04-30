package View;

import Controller.Controller;
import Model.CaseOthello;
import Model.Plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ActionListener {
    private final JButton[][] boutons;
    private final boolean start = false;
    private final JFrame fenetre;

    public View(ActionListener listener) {

        fenetre = new JFrame("Fenetre");

        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel panneauinfo = new JLabel("Information");
        fenetre.add(panneauinfo, BorderLayout.CENTER);
        //fenetre.add(text);

        JLabel p = new JLabel("Points pour noir :");
        JLabel coup = new JLabel("Points pour blanc :");
        fenetre.add(p, BorderLayout.LINE_START);
        fenetre.add(coup, BorderLayout.WEST);


        ImageIcon image = new ImageIcon("Image/othellologo.png");
        Image img = image.getImage().getScaledInstance(100, -1, Image.SCALE_DEFAULT);
        JLabel logoImage = new JLabel(image);

        JPanel plateau = new JPanel(new GridLayout(8, 8));

        boutons = new JButton[8][8];
        for (int i = 0; i < boutons.length; i++) {
            for (int j = 0; j < boutons[i].length; j++) { //Création des boutons
                boutons[i][j] = new JButton();
                boutons[i][j].addActionListener(listener);
                boutons[i][j].setActionCommand(i + "-" + j);
                boutons[i][j].setPreferredSize(new Dimension(100, 100)); //redimensionner les boutons
                plateau.add(boutons[i][j]);
            }
        }

        JButton boutonRecommencer = new JButton("Recommencer");
        boutonRecommencer.addActionListener(this);


        JPanel menuchoix = new JPanel(new FlowLayout(FlowLayout.CENTER));
        menuchoix.add(boutonRecommencer);

        JPanel panneauBas = new JPanel(new GridLayout(2, 1));
        panneauBas.add(panneauinfo);
        panneauBas.add(menuchoix);
        panneauBas.add(coup);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBackground(Color.lightGray);
        panel.add(coup);
        panel.add(p);

        fenetre.setLayout(new BorderLayout());
        fenetre.add(logoImage, BorderLayout.PAGE_START);
        fenetre.add(plateau, BorderLayout.CENTER);
        fenetre.add(panel, BorderLayout.BEFORE_LINE_BEGINS);
        fenetre.add(panneauBas, BorderLayout.PAGE_END);

        fenetre.setLocation(200, 150);
        fenetre.pack();
        fenetre.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) { // bouton recommencer
        View oth = new View(null);
        Controller game = new Controller();
        Plateau pl = new Plateau();
    }

    public void afficherCoupJouer(ImageIcon image, int indiceligne, int indicecolonne) {
//		   boutons[indiceligne][indicecolonne].setEnabled(false); //desactiver le bouton
        boutons[indiceligne][indicecolonne].setIcon(image);
        SwingUtilities.updateComponentTreeUI(fenetre);
    }

    public void show(CaseOthello[][] grille) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                CaseOthello c = grille[i][j];
                afficherCoupJouer(c.getImage(), i, j);
            }
        }
    }
}
