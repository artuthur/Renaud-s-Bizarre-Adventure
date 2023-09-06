package main.view;

import main.Battle;
import main.Color;
import main.Game;
import main.bestiary.Bestiary;
import main.donjon.Theme;
import main.entity.Renaud;
import main.file.FileFinder;
import main.file.FileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class BattleView {
    public final static String FILENAME_BATTLE = "battle.txt";
    private static final String RENAUD = "renaud.txt";
    private static final File FILE_RENAUD = FileFinder.find(RENAUD);
    private static final String TAB = "\t\t\t\t\t";
    private static final String PHRASE_PV = " HP : ";


    public static void afficheBattle(){
        Game.clearScreen();
        System.out.println(FileLoader.load(FILENAME_BATTLE));
        Game.pressToContinue();
        Game.clearScreen();
    }
    
    public static void afficheSprites(Battle bt){
        File file = FileFinder.find(bt.getMob().getFileName());
        if(file == null) return;

        Renaud player = bt.getPlayer();
        Bestiary mob = bt.getMob();
        Theme theme = mob.getTheme();

        StringBuilder sb = new StringBuilder();
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbHp = new StringBuilder();

        try(BufferedReader br1 = new BufferedReader(new FileReader(FILE_RENAUD))){
            try (BufferedReader br2 = new BufferedReader(new FileReader(file))){
                while(br1.ready()){
                    String playerLine = br1.readLine();
                    String mobLine = br2.readLine();
                    mobLine = Color.stringToColor(theme.getColor(), mobLine);

                    sb.append(playerLine + TAB + mobLine + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        String playerName = "Renaud";
        String mobName = mob.getName();

        mobName = Color.stringToColor(theme.getColor(), mobName);

        String phrasePvPlayer = PHRASE_PV + player.getCurrentHp() + " ";
        String phrasePvMob = PHRASE_PV + bt.getFoe().getCurrentHp() + " ";

        int setSpaceRenaudHp = (getMaxCarac(FILE_RENAUD) - ( phrasePvPlayer ).length())/2 ;
        int setSpaceMobHp = (getMaxCarac(file) - ( phrasePvMob ).length())/2 ;

        int setSpaceRenaudName = (getMaxCarac(FILE_RENAUD) - playerName.length())/2 ;
        int setSpaceMobName = (getMaxCarac(file) - ( bt.getMob().getName() ).length())/2 ;

        sbName.append(setSpace( setSpaceRenaudName) + playerName + setSpace(setSpaceRenaudName));
        sbName.append(setSpace(TAB.length()*7));
        sbName.append(setSpace( setSpaceMobName) + mobName + setSpace(setSpaceMobName));

        sbHp.append(setSpacePoint( setSpaceRenaudHp) + phrasePvPlayer + setSpacePoint(setSpaceRenaudHp));
        sbHp.append(setSpace(BattleView.TAB.length()*7));
        sbHp.append(Color.stringToColor(theme.getColor(), setSpacePoint( setSpaceMobHp) + phrasePvMob + setSpacePoint(setSpaceMobHp)));

        System.out.println();
        System.out.println(sb);
        System.out.println(sbName);
        System.out.println(sbHp);

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

    private static String setSpacePoint(int n){
        String s = "";
        for (int i = 0; i < n; i++) {
            s += ".";
        }
        return s;
    }

    private static String setSpace(int n){
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }
}
