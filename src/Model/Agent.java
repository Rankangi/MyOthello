package Model;

public class Agent extends Joueur{

    public Agent(Color couleur){
        super(couleur);
        this.isHuman = false;
    }

    public int[] play(Plateau plateau){
        int [] pos = {0, 0};
        int bestValue = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                int value = plateau.getValue(i, j, couleur);
                if (value > bestValue){
                    pos[0] = i;
                    pos[1] = j;
                    bestValue = value;
                }
            }
        }
        return pos;
    }
}
