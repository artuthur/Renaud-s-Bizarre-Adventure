package main.entity;

import main.bestiary.Bestiary;
import main.effect.Spell;

public class Monster extends Entity{
    private Bestiary mob;
    private Spell spell;

    public Monster(Bestiary mob) {
        super(mob);
        this.mob = mob;
        this.spell = mob.getFirstSpell();
    }

    public Spell getSpell() {
        return spell;
    }
    
    public Bestiary getMob(){
        return mob;
    }

    public boolean isBoss() {
        return mob.isBoss();
    }

    public void stageScale(int stage) {
        setHp((int)(this.getHp() + this.getHp()*(0.2*stage)));
    }
    
    public String toString() {
        return super.toString() + "[firstSpell:" + spell + "]";
    }
}
