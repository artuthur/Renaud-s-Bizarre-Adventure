package main.view;

import main.Color;
import main.entity.Renaud;

public abstract class RenaudView {
    public final static char CHAR_UP_LEFT = GameView.CHAR_UP_LEFT;
    public final static char CHAR_UP_RIGHT = GameView.CHAR_UP_RIGHT;
    public final static char CHAR_DOWN_LEFT = GameView.CHAR_DOWN_LEFT;
    public final static char CHAR_DOWN_RIGHT = GameView.CHAR_DOWN_RIGHT;
    public final static char CHAR_VERTICAL = GameView.CHAR_VERTICAL;
    public final static char CHAR_HORIZONTAL = GameView.CHAR_HORIZONTAL;

    public static Renaud player;

    public static void printPlayerStats() {
        int lengthColor = Color.stringToColor(Color.FG_RED, "").length() +
            Color.stringToColor(Color.FG_BLUE, "").length() + 
            (Color.stringToColor(Color.FG_YELLOW, "").length()*3) + 
            (Color.stringToColor(Color.FG_GREEN, "").length()*2) +
            (Color.stringToColor(Color.FG_PURPLE, "").length()*2);

        String txt = "";
        txt += '│' + stageRoomString() + '│';
        txt += lvlString() + '│';
        txt += expString() + '│';
        txt += hpString() + '│';
        txt += atkString() + '│';
        txt += defString() + '│';
        int width = txt.length()-(2+lengthColor);
        print(txt, width);
    }

    public static void printBattleStats() {
        String txt = "";
        txt += '│' + stageRoomString() + '│';
        txt += lvlString() + '│';
        txt += expString() + '│';
        txt += atkString() + '│';
        txt += defString() + '│';
        int lengthColor = Color.stringToColor(Color.FG_RED, "").length() +
            Color.stringToColor(Color.FG_BLUE, "").length() + 
            (Color.stringToColor(Color.FG_YELLOW, "").length()*3) + 
            (Color.stringToColor(Color.FG_PURPLE, "").length()*2);
        int width = txt.length()-(2+lengthColor);
        print(txt, width);
    }

    public static void print(String txt, int width){
        StringBuilder sb = new StringBuilder();
        addUpLine(sb, width); addNewLine(sb);
        addMiddleLine(sb, txt); addNewLine(sb);;
        addDownLine(sb, width); addNewLine(sb);
        System.out.println(sb);
    }

    public static void addNewLine(StringBuilder sb){
        sb.append("\n");
    }

    private static void addMiddleLine(StringBuilder sb, String line){
        sb.append(line);
    }

    private static void addUpLine(StringBuilder sb, int width){
        sb.append(CHAR_DOWN_RIGHT);
        for (int i = 0; i < width; i++) sb.append(CHAR_HORIZONTAL);
        sb.append(CHAR_DOWN_LEFT);
    }

    private static void addDownLine(StringBuilder sb, int width){
        sb.append(CHAR_UP_RIGHT);
        for (int i = 0; i < width; i++) sb.append(CHAR_HORIZONTAL);
        sb.append(CHAR_UP_LEFT);
    }

    private static String lvlString() {
        return "Niveau:" + Color.stringToColor(Color.FG_YELLOW, "" + player.getLevel());
    }
    private static String expString() {
        return "Expérience:" + Color.stringToColor(Color.FG_YELLOW, "" + player.getExpCurrent()) + "/" + Color.stringToColor(Color.FG_YELLOW, "" + player.getExpNeeded());
    }
    private static String hpString() {
        return "Points de vie:" + Color.stringToColor(Color.FG_GREEN, "" +player.getCurrentHp()) + "/" + Color.stringToColor(Color.FG_GREEN, "" +player.getHp());
    }
    private static String atkString() {
        return "Attaque:" + Color.stringToColor(Color.FG_RED, "" + player.getAtk());
    }
    private static String defString() {
        return "Défense:" + Color.stringToColor(Color.FG_BLUE, "" + player.getDef());
    }
    private static String stageRoomString() {
        return "Stage:" + (Color.stringToColor(Color.FG_PURPLE, "" + (player.getStage()+1))) + "-" + (Color.stringToColor(Color.FG_PURPLE, "" + (player.getRoom()+1)));
    }
}
