package main.bestiary;

import main.Game;
import main.donjon.Theme;

public class BestiaryLoader{
    public final static Theme[] THEMES = Theme.values();
    public final static int EXIT_BESTIARY = 0;

    public static void load() {
        int choice = 0;
        
        System.out.println("Bienvenue dans le bestiaire");
        do {
            Game.clearScreen();
            System.out.println("-> Retourner à l'écran titre ? (press 0)");
            System.out.println();
            System.out.println("Quelle thèmes voulez-vous voir ?");
            System.out.println();
            printThemes();
            System.err.println();
            System.out.print("Veuiller entrer le chiffre corréspondant : ");

            choice = Game.readIntNotNull();

            System.out.println();

            if(choiceThemeIsValid(choice)){
                printBestiary(THEMES[choice - 1]);
            }

        } while (choice != EXIT_BESTIARY);
        Game.startTitleScreen();
    }

    public static boolean choiceThemeIsValid(int index){
        return index >= 1 && index <= THEMES.length;
    }

    public static void printThemes(){
        for (int i = 0; i < THEMES.length; i++){
            System.out.println("Theme " + (i + 1) + " : " + THEMES[i]);
        }
    }

    public static void printBestiary(Theme theme){
        for(Bestiary m : Bestiary.getMobs(theme)){
            if(m.isBoss()){
                System.out.println("!BOSS! " + m.getName());
            }else{
                System.out.println(m.getName());
            }
        }
        Game.pressToContinue();
    }
}