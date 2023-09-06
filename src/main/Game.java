package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.bestiary.BestiaryLoader;
import main.file.FileLoader;
import main.view.GameView;

public class Game {
    public final static String FILENAME_TEST = "Titlescreen.txt";
    public final static String PRESS_TO_CONTINUE = "Appuyez sur 'Entrée' pour continuer";
    public final static String START_GAME = "a";
    public final static String START_BESTIARY = "b";

    public static boolean end = false;

    public static void main(String[] args) {
        startTitleScreen();
    }

    public static void startTitleScreen(){
        clearScreen();
        FileLoader.print(FILENAME_TEST);
        
        do{
            String choice = readStringNotNull();

            if(keyEquality(choice, START_GAME)){ GameView.start(); }
            if(keyEquality(choice, START_BESTIARY)) { BestiaryLoader.load(); }
        }while(!end);
    }

    public static boolean keyEquality(String a, String b){
        return a.toLowerCase().equals(b.toLowerCase());
    }

    public static int readIntNotNull(){
        String s = null;
        try {
            do{
                s = readString();
            }while(s == null || s.isEmpty() || !isNumber(s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(s);
    }

    public static boolean isNumber(String s){
        for(char c : s.toCharArray()) if(!isFigure(c)) return false;
        return true;
    }

    public static boolean isFigure(char c){
        return c >= '0' && c <= '9';
    }

    public static String readStringNotNull(){
        String s = null;
        try{
            do{
                s = readString();
            }while(s == null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return s;
    }

    public static String readString() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void pressToContinue(){
        System.out.println();
        System.out.print(PRESS_TO_CONTINUE);
        Game.readStringNotNull();
        System.out.println();
    }

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
