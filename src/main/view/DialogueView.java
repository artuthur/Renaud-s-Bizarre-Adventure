package main.view;

import main.Game;
import main.file.FileLoader;

public class DialogueView {
    public final static String START_SCREEN = "StartGame.txt";
    public final static String WIN_SCREEN = "WinScreen.txt";
    public final static String DEAD_SCREEN = "DeadScreen.txt";

    public static void startGame(){
        clearScreen();
        FileLoader.print(START_SCREEN);
        Game.pressToContinue();
    }

    public static void nextStage(int stage){
        clearScreen();
        System.out.println("Nextstage : " + stage);
        Game.pressToContinue();
    }
    
    public static void playerDead(){
        clearScreen();
        FileLoader.print(DEAD_SCREEN);
        Game.pressToContinue();
        Game.startTitleScreen();
    }
    
    public static void playerWin(){
        clearScreen();
        System.out.println(WIN_SCREEN);
        Game.pressToContinue();
        Game.startTitleScreen();
    }

    public static void clearScreen(){
        Game.clearScreen();
    }
}