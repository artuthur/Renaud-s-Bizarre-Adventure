package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.bestiary.BestiaryLoader;
import main.donjon.Donjon;
import main.donjon.DonjonGenerator;
import main.entity.Renaud;
import main.file.FileLoader;

public class Game {
    public final static Renaud PLAYER = new Renaud();
    public final static Donjon DONJON = new Donjon();
    public final static DonjonGenerator DONJON_GENERATOR = new DonjonGenerator(DONJON, PLAYER);
    public final static String FILENAME_TEST = "Titlescreen.txt";
    public static boolean end = false;

    public static void main(String[] args) {
        startTitleScreen();
    }

    public static void startTitleScreen(){
        FileLoader.print(FILENAME_TEST);

        String choice = readStringNotNull();

        do{
            if(choice.equals("a")){ startGame(); }
            if(choice.equals("b")) { BestiaryLoader.load(); }
        }while(!end);
    }
    
    public static void startGame(){
        DONJON_GENERATOR.drawDonjon();
    }

    public static int readIntNotNull(){
        String s = null;
        try {
            do{
                s = readString();
            }while(s == "" || !isNumber(s));
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

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
