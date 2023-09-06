package main.effect;

import main.Game;
import main.entity.Renaud;

public class LevelChoice {
    public final static int NB_CHOICES = 3;
    
    public static Bonus[] drawBonuses(Renaud player) {
        Bonus[] res = new Bonus[NB_CHOICES];
        for (int i = 0; i < NB_CHOICES; i++) {
            res[i] = Bonus.random(player.getBonusDrawList());
        }
        return res;
    }

    public static Bonus pickBonus(Renaud player) {
        Bonus[] choices = drawBonuses(player);
        int choice = 0;
        while(choice < 1 || choice > 3) {
            Game.clearScreen();
            for (int i = 0; i < choices.length; i++) {
                System.out.println((i+1) + " : " + choices[i]);
            }
            System.out.println("\n Sélectionnez un bonus : ");
            choice = Game.readIntNotNull();
        }
        return choices[choice-1];
    }

    public static void main(String[] args) {
        Renaud player = new Renaud();
        LevelChoice.pickBonus(player);
    }
}
