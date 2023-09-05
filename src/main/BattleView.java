package main;

import main.bestiary.Bestiary;
import main.file.FileLoader;

import java.io.File;
import java.lang.Thread;

public class BattleView {
    public final static String FILENAME_TEST = "battle.txt";
    private static final String RENAUD = "renaud.txt";
    private static final File FILERENAUD = new File(RENAUD);


    public static void afficheBattle(){
        System.out.println(FileLoader.load(FILENAME_TEST));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        Game.clearScreen();
    }

    public static void afficheSprites(Bestiary mob){
        
    }

    public static void main(String[] args) {
        BattleView.afficheBattle();
        BattleView.afficheSprites(Bestiary.CRS);
    }


}
