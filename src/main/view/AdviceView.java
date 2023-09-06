package main.view;

import java.util.ArrayList;
import java.util.List;

import main.Game;
import main.donjon.Advice;

public abstract class AdviceView {
    public final static String ADVICE_TEXT = "Anecdote/Conseil";

    public final static char CHAR_SPACE = ' ';
    public final static char CHAR_UP_LEFT = GameView.CHAR_UP_LEFT;
    public final static char CHAR_UP_RIGHT = GameView.CHAR_UP_RIGHT;
    public final static char CHAR_DOWN_LEFT = GameView.CHAR_DOWN_LEFT;
    public final static char CHAR_DOWN_RIGHT = GameView.CHAR_DOWN_RIGHT;
    public final static char CHAR_VERTICAL = GameView.CHAR_VERTICAL;
    public final static char CHAR_HORIZONTAL = GameView.CHAR_HORIZONTAL;

    public final static int PADDING_UP = 2;
    public final static int PADDING_DOWN = 2;
    public final static int PADDING_LEFT = 2;
    public final static int PADDING_RIGHT = 2;
    public final static int WIDTH = 200;

    public static void load(Advice advice){
        Game.clearScreen();
        printAdvice(advice);
        Game.pressToContinue();
    }

    public static List<String> cutText(String s){
        List<String> list = new ArrayList<>();
        list.add(s);
        return list;
    }

    public static void printAdvice(Advice a){
        printAdvice(cutText(a.getHelp()));
    }

    public static void printAdvice(List<String> list){
        StringBuilder sb = new StringBuilder();
        addUpLine(sb);
        for(int y = - PADDING_UP - 2; y < list.size() + PADDING_DOWN; y++){
            sb.append(CHAR_VERTICAL);
            addSpace(sb, PADDING_LEFT);
            for(int x = 0; x < WIDTH; x++){
                if(y == - PADDING_UP - 1 && x == WIDTH / 2 - ADVICE_TEXT.length() / 2){
                    sb.append(ADVICE_TEXT);
                    x += ADVICE_TEXT.length();
                }
                if(y >= 0 && y < list.size()){
                    String currentLine = list.get(y);
                    if(x == WIDTH / 2 - currentLine.length() / 2){
                        sb.append(currentLine);
                        x += currentLine.length();
                    }
                }
                addSpace(sb);
            }
            addSpace(sb, PADDING_RIGHT);
            sb.append(CHAR_VERTICAL);
            addNewLine(sb);
        }
        addDownLine(sb);
        System.out.println(sb);
    }

    public static void addNewLine(StringBuilder sb){
        sb.append("\n");
    }

    public static void addSpace(StringBuilder sb){
        addSpace(sb, 1);
    }

    public static void addSpace(StringBuilder sb, int count){
        for(int i = 0; i < count; i++) sb.append(CHAR_SPACE);
    }

    private static void addUpLine(StringBuilder sb){
        addLine(sb, CHAR_DOWN_RIGHT, CHAR_HORIZONTAL, CHAR_DOWN_LEFT);
    }

    private static void addDownLine(StringBuilder sb){
        addLine(sb, CHAR_UP_RIGHT, CHAR_HORIZONTAL, CHAR_UP_LEFT);
    }

    private static void addLine(StringBuilder sb, char a, char b, char c){
        sb.append(a);
        for (int i = 0; i < WIDTH + PADDING_LEFT + PADDING_RIGHT; i++) sb.append(b);
        sb.append(c);
        addNewLine(sb);
    }
}