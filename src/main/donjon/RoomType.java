package main.donjon;

import main.Color;

public enum RoomType {
    ADVICE(Color.FG_BLUE, '\u2605'), /* 1 : '\u2606' / 2 : '\u2605' */
    ENEMY(Color.FG_RED, '\u265C'), /* '\u2656' : ? / 2 : '\u265C' */
    BOSS(Color.FG_YELLOW, '\u265B'); /* 1 : '\u2655' / 2 : '\u265B' */

    private Color color;
    private char cara;

    private RoomType(Color color, char cara){
        this.color = color;
        this.cara = cara;
    }

    public Color getColor(){
        return color;
    }

    public char getCara(){
        return cara;
    }

    public String toString(){
        return getClass().getSimpleName() + "[color:" + color + ", cara:" + cara + "]";
    }
}