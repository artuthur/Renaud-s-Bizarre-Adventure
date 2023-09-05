package main;

public enum Bestiary{
    CRS(Theme.INTERIEUR, "crs.txt", 100 /* vie */, 20 /* atq */, 50 /* def */);

    private Theme theme;
    private String fileName;
    private int health;
    private int attack;
    private int defense;

    private Bestiary(Theme theme, String fileName, int health, int attack, int defense){
        this.theme = theme;
        this.fileName = fileName;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public Theme getTheme(){
        return theme;
    }

    public String getFileName(){
        return fileName;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getAttack(){
        return attack;
    }

    public int getDefense(){
        return defense;
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", fileName:" + fileName + ", health:" + health + ", attack:" + attack + ", defense:" + defense + "]";
    }
}