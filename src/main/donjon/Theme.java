package main.donjon;

import main.Color;

public enum Theme{
    INTERIEUR(Color.FG_YELLOW),
    EDUCATION(Color.FG_BLUE),
    OPPOSITION(Color.FG_PURPLE),
    CULTURE(Color.FG_GREEN),
    FINALE(Color.FG_RED);

    private Color color;

    private Theme(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public static int getSize(){
        return values().length;
    }
}
