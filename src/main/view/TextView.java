package main.view;

import java.util.ArrayList;
import java.util.List;

import main.Game;
import main.donjon.Advice;
import main.file.FileLoader;

public abstract class TextView {
    public final static char SEPARATOR_TEXT = ' ';
    public final static char NEW_LINE = '\n';
    public final static String ADVICE_TEXT = "Anecdote/Conseil";
    public final static String HISTORY_TEXT = "Histoire";

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
    public final static int WIDTH = 100;

    public static void clearScreen(){
        Game.clearScreen();
    }

    public static void printAdvice(Advice advice){
        clearScreen();
        System.out.println(loadText(ADVICE_TEXT, advice.getHelp()));
    }

    public static void printHistory(String path, String fileName){
        String s = FileLoader.load(path, fileName);
        clearScreen();
        System.out.println(loadText(HISTORY_TEXT, s.replace(NEW_LINE, SEPARATOR_TEXT)));
    }

    public static List<String> cutText(String s){
        char[] array = s.toCharArray();
        int lengthMax = WIDTH - (PADDING_LEFT + PADDING_RIGHT + 2);        
        return getTextWidthSeparator(array, getIndicesOfLengthMaxWithSeparator(lengthMax, array, SEPARATOR_TEXT));
    }

    public static List<String> getTextWidthSeparator(char[] array, List<Integer> separators){
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < array.length; i++) {
            if(i == array.length - 1){
                sb.append(array[i]);
                list.add(sb.toString());
            }else if(separators.contains(i)){
                list.add(sb.toString());
                sb = new StringBuilder();
            }else sb.append(array[i]);
        }
        
        return list;
    }
    
    public static List<Integer> getIndicesOfLengthMaxWithSeparator(int lengthMax, char[] array, char separator){
        List<Integer> list = new ArrayList<>();
        int count = 0;
        int lastSeparator = 0;

        for(int i = 0; i < array.length; i++){
            char c = array[i];
            if(c == SEPARATOR_TEXT) lastSeparator = i;
            if(count >= lengthMax && array[i] == separator && !list.contains(lastSeparator)){
                list.add(lastSeparator);
                count = 0;
            }else count++;
        }

        return list;
    }

    public static String loadText(String type, String s){
        return loadText(type, cutText(s));
    }

    public static String loadText(String type, List<String> list){
        StringBuilder sb = new StringBuilder();
        addUpLine(sb);
        for(int y = - PADDING_UP - 2; y < list.size() + PADDING_DOWN; y++){
            sb.append(CHAR_VERTICAL);
            addSpace(sb, PADDING_LEFT);
            for(int x = 0; x < WIDTH; x++){
                if(y == - PADDING_UP - 1 && (x == WIDTH / 2 - type.length() / 2 || WIDTH / 2 - type.length() - 2 < 0)){
                    sb.append(type);
                    x += type.length();
                }
                if(y >= 0 && y < list.size()){
                    String currentLine = list.get(y);
                    if(x == WIDTH / 2 - currentLine.length() / 2){
                        sb.append(currentLine);
                        x += currentLine.length();
                    }
                }
                if(x == WIDTH - PADDING_RIGHT - 1 && x % 2 == 0) continue;
                addSpace(sb);
            }
            addSpace(sb, PADDING_RIGHT);
            sb.append(CHAR_VERTICAL);
            addNewLine(sb);
        }
        addDownLine(sb);
        return sb.toString();
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