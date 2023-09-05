package main.entity;

import main.bestiary.Bestiary;
import main.effect.Spell;

public class Monster extends Entity{
    private Spell spell;

    public Monster(Bestiary mob) {
        super(mob.getHealth(), mob.getHealth(), mob.getDefense(), mob.getName());
        this.spell = mob.getFirstSpell();
    }

    public void stageScale(int stage) {
        setHp((int)(this.getHp()*(1+0.2*stage)));
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }
}
