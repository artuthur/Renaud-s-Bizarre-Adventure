package main;

import main.bestiary.Bestiary;
import main.file.FileFinder;
import main.file.FileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread;

public class BattleView {
    public final static String FILENAME_BATTLE = "battle.txt";
    private static final String RENAUD = "renaud.txt";
    private static final File FILERENAUD = FileFinder.find(RENAUD);


    public static void afficheBattle(){
        System.out.println(FileLoader.load(FILENAME_BATTLE));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        Game.clearScreen();
    }

    public static void afficheSprites(Bestiary mob){
        File file = FileFinder.find(mob.getFileName());
        
        StringBuilder sb = new StringBuilder();

        try(BufferedReader br1 = new BufferedReader(new FileReader(BattleView.FILERENAUD))){

            try (BufferedReader br2 = new BufferedReader(new FileReader(file))){
                while(br1.ready()){
                    System.out.println(br1.readLine()+ "\t" + br2.readLine());
                    sb.append(br1.readLine() + "\t\t" + br2.readLine() + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ta mere");
        sb.toString();

    }

    public static void main(String[] args) {
        BattleView.afficheBattle();
        BattleView.afficheSprites(Bestiary.CRS);
    }


}
