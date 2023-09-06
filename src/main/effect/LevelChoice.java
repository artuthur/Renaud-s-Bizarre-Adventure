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
            for (int i = 0; i < choices.length; i++) {
                if(choices[i].getBonusType().equals(BonusType.BUFF)) {
                    System.out.println((i+1) + " : " + choices[i].getName() + ", +" + 
                        choices[i].getValue() + "% en " + choices[i].getSpellType() +
                        " (Augmentation de statistique)");
                } else if(choices[i].getBonusType().equals(BonusType.SPELL_SCALING)) {
                    System.out.println((i+1) + " : " + choices[i].getName() + ", inflige " + 
                        choices[i].getValue() + "% de " + choices[i].getSpellType() +
                        ", Tours de recharge : " + choices[i].getCooldown() +
                        " (Sort dépendant d'une statistique)");
                } else {
                    System.out.println((i+1) + " : " + choices[i].getName() + ", inflige " + 
                        choices[i].getValue() + " dégâts" + ", Tours de recharge : " + 
                        choices[i].getCooldown() + " (Sort à dégât fixe)");             
                }
            }
            System.out.println("\n Sélectionnez un bonus : ");
            choice = Game.readIntNotNull();
        }
        return choices[choice-1];
    }
}
