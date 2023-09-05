package main;

import java.util.ArrayList;
import java.util.List;

public class Renaud extends Entity{
    public final static int BASE_HP = 100;
    public final static int BASE_ATK = 20;
    public final static int BASE_DEF = 50;
    public final static int BASE_LEVEL = 1;
    public final static int BASE_EXP = 0;
    public final static int BASE_NEEDED_EXP = 100;
    public final static int BASE_STAGE = 0;
    public final static int BASE_ROOM = 0;

    private List<Bonus> bonusList;
    private List<Bonus> bonusDrawList;
    private List<Spell> learnedSpells;
    private int level;
    private int expNeeded;
    private int expCurrent;
    private int stage;
    private int room;

    public Renaud() {
        super(BASE_HP, BASE_ATK, BASE_DEF, "Renaud");
        this.bonusList = new ArrayList<Bonus>();
        this.bonusDrawList = new ArrayList<Bonus>();
        this.learnedSpells = new ArrayList<Spell>();
        this.level = BASE_LEVEL;
        this.expNeeded = BASE_NEEDED_EXP;
        this.expCurrent = BASE_EXP;
        this.stage = BASE_STAGE;
        this.room = BASE_ROOM;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Bonus> bonusList) {
        this.bonusList = bonusList;
    }

    public void addBonusToRenaud(Bonus bonus) {
        bonusList.add(bonus);
    }

    public List<Bonus> getBonusDrawList() {
        return bonusDrawList;
    }

    public void setBonusDrawList(List<Bonus> bonusDrawList) {
        this.bonusDrawList = bonusDrawList;
    }

    public void addBonusToList(Bonus bonus) {
        bonusDrawList.add(bonus);
    }

    public List<Spell> getLearnedSpells() {
        return learnedSpells;
    }

    public void setLearnedSpells(List<Spell> learnedSpells) {
        this.learnedSpells = learnedSpells;
    }

    public boolean addLearnedSpells(Spell spell) {
        return learnedSpells.add(spell);
    }

    public int getExpNeeded() {
        return expNeeded;
    }

    public void setExpNeeded(int expNeeded) {
        this.expNeeded = expNeeded;
    }

    public int getExpCurrent() {
        return expCurrent;
    }

    public void setExpCurrent(int expCurrent) {
        this.expCurrent = expCurrent;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getLevel() {
        return level;
    }

    public void giveExp(int exp) {
        expCurrent += exp;
        while(expCurrent >= expNeeded){
            expCurrent -= expNeeded;
            nextLevel();
        }
    }
    
    public void nextLevel() {
        level++;
        expNeeded *= 1.2;
    }
}
