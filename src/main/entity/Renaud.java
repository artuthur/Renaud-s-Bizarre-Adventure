package main.entity;

import java.util.ArrayList;
import java.util.List;

import main.Game;
import main.effect.Bonus;
import main.effect.BonusType;
import main.effect.LevelChoice;

public class Renaud extends Entity{
    public final static int BASE_HP = 100;
    public final static int BASE_ATK = 20;
    public final static int BASE_DEF = 20;
    public final static int BASE_LEVEL = 1;
    public final static int BASE_EXP = 0;
    public final static int BASE_NEEDED_EXP = 100;
    public final static int BASE_STAGE = 0;
    public final static int BASE_ROOM = 0;

    private List<Bonus> bonusList;
    private List<Bonus> bonusDrawList;
    private List<Bonus> learnedSpells;

    private int atk;
    private int level;
    private int expNeeded;
    private int expCurrent;
    private int stage;
    private int room;

    public Renaud() {
        super("Renaud", BASE_HP, BASE_DEF);
        this.bonusList = new ArrayList<Bonus>();
        this.bonusDrawList = Bonus.getBonusList();
        this.learnedSpells = new ArrayList<Bonus>();
        this.atk = BASE_ATK;
        this.level = BASE_LEVEL;
        this.expNeeded = BASE_NEEDED_EXP;
        this.expCurrent = BASE_EXP;
        this.stage = BASE_STAGE;
        this.room = BASE_ROOM;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void addBonusToRenaud(Bonus bonus) {
        bonusList.add(bonus);
        if (!bonus.getBonusType().equals(BonusType.BUFF)) {
            bonusDrawList.remove(bonus);
            learnedSpells.add(bonus);
        } 
        bonus.calcBuffOrValue(this);
    }

    public List<Bonus> getBonusDrawList() {
        return bonusDrawList;
    }

    public List<Bonus> getLearnedSpells() {
        return learnedSpells;
    }

    public boolean addLearnedSpells(Bonus spell) {
        return learnedSpells.add(spell);
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
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

    public void nextRoom(){
        int roomMax = Game.DONJON_GENERATOR.getDonjon().getRoomsCount(stage);
        room++;
        while(room >= roomMax){
            room -= roomMax;
            nextStage();
        }
    }

    public void nextStage(){
        stage++;
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

    public boolean applyBonus(Bonus bonus) {
        if(bonus.getBonusType().equals(BonusType.BUFF)) {
            bonus.calcBuffOrValue(this);
            return true;
        }
        return this.addLearnedSpells(bonus) && bonusDrawList.remove(bonus);
    }
    
    public void nextLevel() {
        Game.clearScreen();
        level++;
        expNeeded *= 1.2;
        System.out.println("Vous êtes passé niveau " + getLevel() + " !");
        applyBonus(LevelChoice.pickBonus(this));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + super.toString() + "[atk:" + atk + ", level:" + level + ", expNeeded:" + expNeeded
        + ", expCurrent:" + expCurrent + ", stage:" + stage + ", room:" + room + ", bonusList:" + bonusList.toString() 
        + ", bonusDrawList:" + bonusDrawList.toString() + ", learnedSpells:" + learnedSpells + "]";
    }
}
