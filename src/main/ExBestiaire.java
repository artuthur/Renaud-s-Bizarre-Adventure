package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExBestiaire{
    public static void main(String[] args) {
        clearScreen();
        String choix;

        System.out.println("Bienvenue dans le bestiaire");
        do {
            System.out.println("Quelle thèmes voulez-vous voir ?");
            System.out.println();
            System.out.println("Theme 1 : INTERIEUR");
            System.out.println("Theme 2 : EDUCATION");
            System.out.println("Theme 3 : ECOLOGIE");
            System.out.println("Theme 4 : CULTURE");
            System.out.println("Theme 5 : FINALE");
            System.err.println();
            System.out.println("Veuiller entrer le chiffre corréspondant");
            choix = read();
            if(choix == null) continue;
            if (choix.equals("1")) {
                System.out.println("CRS (Chevalier Republicain Sécuritair)");
                System.out.println("ARS (Archer Republicain Séuritair)");
                System.out.println("BRAVE-M");
                System.out.println("Chien de garde");
                System.out.println("!BOSS! Gérald Gardemin");
            }else{
                System.out.println("Ce bestiere n'est pas fini !");
            }

        } while (choix == null);


    }
    
    public static String read(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            return br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}