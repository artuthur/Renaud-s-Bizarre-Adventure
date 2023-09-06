package main.effect;

import main.entity.Renaud;

public class LevelChoice {
    public final static int NB_CHOICES = 3;
    
    static Bonus[] drawBonuses(Renaud player) {
        Bonus[] res = new Bonus[NB_CHOICES];
        for (int i = 0; i < NB_CHOICES; i++) {
            res[i] = Bonus.random(player.getBonusDrawList());
        }
        return res;
    }

    public static void main(String[] args) {
        Renaud player = new Renaud();
        Bonus[] choices = drawBonuses(player);
        for (Bonus b : choices) {
            System.out.println(b);   
        }
    }
}
