package main.entity;

public class Entity {
    private int hp;
    private int currentHp;
    private int def;
    private final String NAME;

    
    public Entity(int hp, int currentHp, int def, String name) {
        this.hp = hp;
        this.currentHp = currentHp;
        this.def = def;
        NAME = name;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getDef() {
        return def;
    }
    public void setDef(int def) {
        this.def = def;
    }

    public String toString() {
        return NAME + "[hp=" + hp + ";currentHp=" + currentHp + "def=" + def + "]";
    }
}