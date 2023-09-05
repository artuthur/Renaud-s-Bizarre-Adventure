package main.bestiary;

import main.donjon.Theme;
import main.effect.Spell;

public enum Bestiary{
    CRS(Theme.INTERIEUR, "CRS", "crs.txt", 40, 40, Spell.COUP_DE_MATRAQUE);

    private Theme theme;
    private String name;
    private String fileName;
    private int health;
    private int defense;
    private Spell firstSpell;
    private Spell secondSpell;

    private Bestiary(Theme theme, String name, String fileName, int health, int defense, Spell firstSpell, Spell secondSpell){
        this.theme = theme;
        this.name = name;
        this.fileName = fileName;
        this.health = health;
        this.defense = defense;
        this.firstSpell = firstSpell;
        this.secondSpell = secondSpell;
    }

    private Bestiary(Theme theme, String name, String fileName, int health, int defense, Spell firstSpell){
        this(theme, name, fileName, health, defense, firstSpell, null);
    }

    public Theme getTheme(){
        return theme;
    }

    public String getName(){
        return name;
    }

    public String getFileName(){
        return fileName;
    }
    
    public int getHealth(){
        return health;
    }

    public int getDefense(){
        return defense;
    }

    public Spell getFirstSpell(){
        return firstSpell;
    }

    public Spell getSecondSpell(){
        return secondSpell;
    }

    public boolean isBoss(){
        return secondSpell == null ? true : false;
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", fileName:" + fileName + ", health:" + health + ", defense:" + defense + ", firstSpell:" + firstSpell + ", secondSpell:" + secondSpell + "]";
    }
}