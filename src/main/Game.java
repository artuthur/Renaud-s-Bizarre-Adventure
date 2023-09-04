package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private final static String SEPARATOR = File.separator;
    private final static String PATH = "res" + SEPARATOR;
    public final static String FILENAME_TEST = "Titlescreen.txt";

    public static void main(String[] args) {
        System.out.println(load(FILENAME_TEST));
        String choice = read();
        if (choice.charAt(0) == 'b') {
            ExBestiaire.main(args);
        }
    }

    public static String load(String fileName){
        StringBuilder sb = new StringBuilder();
        File file = new File(PATH + fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while(br.ready()){
                sb.append(br.readLine() + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return sb.toString();
    }

    public static String read(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            return br.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
