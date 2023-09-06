package main;

import main.bestiary.Bestiary;
import main.entity.Renaud;
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
    private static final String TAB = "\t\t\t\t\t";
    private static final String phrase_pv = " HP : ";


    public static void afficheBattle(){
        System.out.println(FileLoader.load(FILENAME_BATTLE));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        Game.clearScreen();
    }

    public static void afficheSprites(Battle bt){
        File file = FileFinder.find(bt.getMob().getFileName());
        

        try(BufferedReader br1 = new BufferedReader(new FileReader(BattleView.FILERENAUD))){

            try (BufferedReader br2 = new BufferedReader(new FileReader(file))){
                while(br1.ready()){
                    System.out.println(br1.readLine() + BattleView.TAB + br2.readLine());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        int pv_player = bt.getPlayer().getHp();
        String phrasePvPlayer = BattleView.phrase_pv + pv_player;
        int setSpaceFirt = (getMaxCarac(BattleView.FILERENAUD) - ( phrasePvPlayer ).length())/2 ;
        sb.append(setSpace( setSpaceFirt) + phrasePvPlayer + setSpace(setSpaceFirt));
        System.out.println(sb.toString());
    }

    private static int getMaxCarac(File f){
        int max =0;
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            while(br.ready()){
                String temp = br.readLine();
                if(temp.length()>max){
                    max = temp.length();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return max;
    }

    private static String setSpace(int n){
        String s = "";
        for (int i = 0; i < n; i++) {
            s += ".";
        }
        return s;
    }

    public static void main(String[] args) {
        Battle bt = new Battle(new Renaud(), Bestiary.CRS);
        BattleView.afficheBattle();
        BattleView.afficheSprites(bt);
    }


}
