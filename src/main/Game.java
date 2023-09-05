package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import main.donjon.Donjon;
import main.donjon.DonjonGenerator;

public class Game {
    public final static String FILENAME_TEST = "Titlescreen.txt";

    public static void main(String[] args) {
        /*
        System.out.println(FileLoader.load(FILENAME_TEST));
        String choice = readStringNotNull();
        if (choice.charAt(0) == 'b') {
            BestiaryLoader.load();
        }
        */
        Donjon donjon = new Donjon();
        donjon.print();
        // DonjonGenerator donjonGenerator = new DonjonGenerator(donjon);
        // donjonGenerator.loadCurrentRoom();
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

    public static int random(int min, int max){
        return min + (int)((max - min + 1) * Math.random());
    }
}
