package main.donjon;

import main.Color;

public enum RoomType {
    ADVICE(Color.FG_BLUE, '?'), /* 1 : ? / 2 : ? */
    ENEMY(Color.FG_RED, '?'), /* 1 : ? / 2 : ? */
    BOSS(Color.FG_YELLOW, '?'); /* 1 : ? / 2 : ? */

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