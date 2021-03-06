package Model;

import java.util.Arrays;

public class Plateau {

    private final CaseOthello[][] grille = new CaseOthello[8][8];

    public void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grille[i][j] = new CaseOthello();
            }
        }
        grille[3][3] = new CaseOthello(Color.BLANC);
        grille[4][4] = new CaseOthello(Color.BLANC);
        grille[3][4] = new CaseOthello(Color.NOIR);
        grille[4][3] = new CaseOthello(Color.NOIR);
    }

    public Plateau() {
        init();
    }


    public CaseOthello[][] getGrille() {
        return grille;
    }

    public boolean verifierdeplacement(int indiceligne, int indicecolonne, Color couleur, boolean change) {
        if (grille[indiceligne][indicecolonne].getCouleur() != null) {
            return false;
        }
        int[] directionMax = new int[8];
        Arrays.fill(directionMax, 0);
        boolean[] directionCheck = new boolean[8];
        Arrays.fill(directionCheck, true);
        int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
        for (int dist = 1; dist <= 8; dist++) {
            for (int direction = 0; direction < 8; direction++) {
                if (directionCheck[direction]) {
                    int newI = indiceligne + dist * di[direction];
                    int newJ = indicecolonne + dist * dj[direction];
                    if (newI < 0 || newI >= 8 || newJ < 0 || newJ >= 8) {
                        directionMax[direction] = 0;
                        directionCheck[direction] = false;
                    } else {
                        CaseOthello c = grille[newI][newJ];
                        if (c.getCouleur() == null) {
                            directionCheck[direction] = false;
                            directionMax[direction] = 0;
                        } else if (c.getCouleur() == couleur) {
                            directionCheck[direction] = false;
                            if (!change) {
                                return true;
                            }
                        } else {
                            directionMax[direction]++;
                        }
                    }
                }
            }
        }
        if (!change) {
            return false;
        }
        boolean flag = false;
        for (int direction = 0; direction < 8; direction++) {
            int dmax = directionMax[direction];
            if (dmax != 0) {
                flag = true;
                for (int k = 1; k <= dmax; k++) {
                    int newI = indiceligne + k * di[direction];
                    int newJ = indicecolonne + k * dj[direction];
                    grille[newI][newJ] = new CaseOthello(couleur);
                }
                grille[indiceligne][indicecolonne] = new CaseOthello(couleur);
            }
        }
        return flag;
    }

    public boolean isfinished(Joueur joueur) {
        boolean oldjoueur = false, nextjoueur;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                nextjoueur = verifierdeplacement(i, j, joueur.getCouleur().next(), false);
                if (!oldjoueur) {
                    oldjoueur = verifierdeplacement(i, j, joueur.getCouleur(), false);
                }
                if (nextjoueur) {
                    return false;
                }
            }
        }
        return !oldjoueur;
    }

    public int[] score() {
        int[] score = new int[2];
        for (CaseOthello[] ligne : grille) {
            for (CaseOthello c : ligne) {
                Color couleur = c.getCouleur();
                if (couleur != null) {
                    switch (couleur) {
                        case BLANC -> score[0]++;
                        case NOIR -> score[1]++;
                    }
                }
            }
        }
        return score;
    }

    public int getValue(int indiceligne, int indicecolonne, Color couleur) {
        if (grille[indiceligne][indicecolonne].getCouleur() != null) {
            return 0;
        }
        int[] directionMax = new int[8];
        Arrays.fill(directionMax, 0);
        boolean[] directionCheck = new boolean[8];
        Arrays.fill(directionCheck, true);
        int[] di = {-1, -1, 0, 1, 1, 1, 0, -1}, dj = {0, 1, 1, 1, 0, -1, -1, -1};
        for (int dist = 1; dist <= 8; dist++) {
            for (int direction = 0; direction < 8; direction++) {
                if (directionCheck[direction]) {
                    int newI = indiceligne + dist * di[direction];
                    int newJ = indicecolonne + dist * dj[direction];
                    if (newI < 0 || newI >= 8 || newJ < 0 || newJ >= 8) {
                        directionMax[direction] = 0;
                        directionCheck[direction] = false;
                    } else {
                        CaseOthello c = grille[newI][newJ];
                        if (c.getCouleur() == null) {
                            directionCheck[direction] = false;
                            directionMax[direction] = 0;
                        } else if (c.getCouleur() == couleur) {
                            directionCheck[direction] = false;
                        } else {
                            directionMax[direction]++;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int value : directionMax) {
            sum += value;
        }
        return sum;
    }
}
