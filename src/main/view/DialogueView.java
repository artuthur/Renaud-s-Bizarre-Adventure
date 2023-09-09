package main.view;

import java.io.File;

import main.Game;
import main.bestiary.Bestiary;
import main.donjon.Theme;
import main.file.FileFinder;
import main.file.FileLoader;

public abstract class DialogueView {
    public final static String PATH = FileFinder.PATH + "lore" + File.separator;
    public final static String START_SCREEN = "StartGame.txt";
    public final static String WIN_SCREEN = "WinScreen.txt";
    public final static String DEAD_SCREEN = "DeadScreen.txt";

    public final static String START_BOSS_INTERIEUR = "Start_Boss_INTERIEUR.txt";
    public final static String START_BOSS_EDUCATION = "Start_Boss_EDUCATION.txt";
    public final static String START_BOSS_OPPOSITION = "Start_Boss_OPPOSITION.txt";
    public final static String START_BOSS_CULTURE = "Start_Boss_CULTURE.txt";
    public final static String START_BOSS_FINALE = "Start_Boss_FINALE.txt";

    public final static String START_INTERIEUR = "Start_INTERIEUR.txt";
    public final static String START_EDUCATION = "Start_EDUCATION.txt";
    public final static String START_OPPOSITION = "Start_OPPOSITION.txt";
    public final static String START_CULTURE = "Start_CULTURE.txt";
    public final static String START_FINALE = "Start_FINALE.txt";

    public static void startGame(){
        clearScreen();
        print(START_SCREEN);
        Game.pressToContinue();
    }

    public static void startBoss(Bestiary mob){
        clearScreen();
        String fileName = null;
        switch(mob.getTheme()){
            case INTERIEUR: fileName = START_BOSS_INTERIEUR; break;
            case EDUCATION: fileName = START_BOSS_EDUCATION; break;
            case OPPOSITION: fileName = START_BOSS_OPPOSITION; break;
            case CULTURE: fileName = START_BOSS_CULTURE; break;
            case FINALE: fileName = START_BOSS_FINALE; break;
        }
        print(fileName);
        Game.pressToContinue();
    }

    public static void startStage(Theme theme){
        clearScreen();
        String fileName = null;
        switch(theme){
            case INTERIEUR: fileName = START_CULTURE; break;
            case EDUCATION: fileName = START_EDUCATION; break;
            case OPPOSITION: fileName = START_OPPOSITION; break;
            case CULTURE: fileName = START_CULTURE; break;
            case FINALE: fileName = START_FINALE; break;
        }
        print(fileName);
        Game.pressToContinue();
    }
    
    public static void playerDead(){
        clearScreen();
        FileLoader.print(DEAD_SCREEN);
        Game.pressToContinue();
    }
    
    public static void playerWin(){
        clearScreen();
        FileLoader.print(WIN_SCREEN);
        Game.pressToContinue();
    }

    public static void print(String fileName){
        TextView.printHistory(PATH, fileName);
    }

    public static void clearScreen(){
        Game.clearScreen();
    }
}