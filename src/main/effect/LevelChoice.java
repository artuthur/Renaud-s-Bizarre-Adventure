package main.effect;

import main.Game;
import main.entity.Renaud;
import main.view.RenaudView;

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
        String res;
        while(choice < 1 || choice > 3) {
            Game.clearScreen();
            RenaudView.printPlayerStats();
            res = "";
            for (int i = 0; i < choices.length; i++) {
                res += (i+1) + " : " + choices[i].getName() + ", ";
                if(choices[i].getBonusType().equals(BonusType.BUFF)) {
                    res += "ajoute +";
                } else if(choices[i].getUseType().equals(UseType.DAMAGE)) {
                    res += "inflige ";
                } else {
                    res += "soigne ";
                }
                if(choices[i].getBonusType().equals(BonusType.BUFF)) {
                    res += choices[i].getValue() + "% en " + choices[i].getSpellType() +
                        " (Augmentation de statistique) \n";
                } else if(choices[i].getBonusType().equals(BonusType.SPELL_SCALING)) {
                    res += choices[i].getValue() + "% de la stat "  + choices[i].getSpellType() +
                        ", Tours de recharge : " + choices[i].getCooldown() +
                        " (Sort dépendant d'une statistique) \n";
                } else {
                    res += choices[i].getValue() + " points de vie, Tours de recharge : " + 
                        choices[i].getCooldown() + " (Sort à dégât fixe) \n";             
                }
            }
            System.out.print(res + "\n Sélectionnez un bonus : ");
            choice = Game.readIntNotNull();
        }
        return choices[choice-1];
    }
}
