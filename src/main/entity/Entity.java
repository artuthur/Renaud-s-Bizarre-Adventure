package main.entity;

import main.bestiary.Bestiary;

public class Entity {
    private int hp;
    private int currentHp;
    private int def;

    
    public Entity(int hp, int def) {
        this.hp = hp;
        this.currentHp = hp;
        this.def = def;
    }

    public Entity(Bestiary mob){
        this(mob.getHealth(), mob.getDefense());
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
        return getClass().getSimpleName() + "[hp:" + hp + ", currentHp:" + currentHp + ", def:" + def + "]";
    }
}