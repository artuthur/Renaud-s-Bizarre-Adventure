package main.view;

import main.Game;
import main.donjon.Advice;

public class AdviceView {
    public static void load(Advice advice){
        Game.clearScreen();
        System.out.println("Advice");
        System.out.println(advice.getHelp());
        Game.pressToContinue();
    }
}
