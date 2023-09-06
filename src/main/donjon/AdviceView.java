package main.donjon;

import main.Game;

public class AdviceView {
    public static void load(Advice advice){
        Game.clearScreen();
        System.out.println("Advice");
        System.out.println(advice.getHelp());
        Game.pressToContinue();
    }
}
