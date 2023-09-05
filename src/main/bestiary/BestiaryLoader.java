package main.bestiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BestiaryLoader{
    public static void load() {
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
            if (choix.equals("1")) {
                System.out.println("CRS (Chevalier Republicain Sécuritair)");
                System.out.println("ARS (Archer Republicain Séuritair)");
                System.out.println("BRAVE-M");
                System.out.println("Chien de garde");
                System.out.println("!BOSS! Gérald Gardemin");
            }else if (choix.equals("2")){
                System.out.println("Araignée de bibliothèque");
                System.out.println("Professeur de magie");
                System.out.println("Professeur d'EPS");
                System.out.println("Livre scolaire magique");
                System.out.println("!BOSS! Jean Merlin Blanquer");
            }else if (choix.equals("3")){
                System.out.println("Robe Jaune");
                System.out.println("Chaosos");
                System.out.println("Révolutionnaire");
                System.out.println("Casseur");
                System.out.println("!BOSS! Jean Luc l'Anarchon");
            }else if (choix.equals("4")){
                System.out.println("Succube");
                System.out.println("Playgirl");
                System.out.println("Vampirette");
                System.out.println("Esclave Golem");
                System.out.println("!BOSS! Marlène Lilithia");
            }else if (choix.equals("4")){
                System.out.println("Gérald Gardemin");
                System.out.println("Jean Merlin Blanquer");
                System.out.println("Jean Luc l'Anarchon");
                System.out.println("Marlène Lilithia");
                System.out.println("!BOSS! Archi Roi Démon Immortel Emanuelle Jupiter Macrongue");
            }

        } while (choix.equals("0"));


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