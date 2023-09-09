package main.entity;

import java.util.ArrayList;
import java.util.List;

import main.Game;
import main.effect.Bonus;
import main.effect.BonusType;
import main.effect.LevelChoice;

public class Renaud extends Entity implements IEntity{
    public final static String PLAYER_NAME = "Renaud";
    public final static String PLAYER_MINI_MAP = "ici";
    public final static int BASE_HP = 125;
    public final static int BASE_ATK = 15;
    public final static int BASE_DEF = 30;
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
        super(BASE_HP, BASE_DEF);
        this.bonusList = new ArrayList<Bonus>();
        this.bonusDrawList = Bonus.getBonusList();
        this.learnedSpells = new ArrayList<Bonus>();
        this.atk = BASE_ATK;
        this.level = BASE_LEVEL;
        this.expNeeded = BASE_NEEDED_EXP;
        this.expCurrent = BASE_EXP;
        this.stage = BASE_STAGE;
        this.room = BASE_ROOM;
        addBonusToRenaud(Bonus.LANCE_DE_BRIQUE);
    }

    @Override
    public String getName(){ return PLAYER_NAME; }
    public List<Bonus> getBonusList() { return bonusList; }
    public List<Bonus> getBonusDrawList() { return bonusDrawList; }
    public List<Bonus> getLearnedSpells() { return learnedSpells; }
    public int getAtk() { return atk; }
    public int getExpNeeded() { return expNeeded; }
    public int getExpCurrent() { return expCurrent; }
    public int getStage() { return stage; }
    public int getRoom() { return room; }
    
    public void setAtk(int atk) { this.atk = atk; }
    public void setExpNeeded(int expNeeded) { this.expNeeded = expNeeded; }
    public void setExpCurrent(int expCurrent) { this.expCurrent = expCurrent; }

    public boolean addLearnedSpells(Bonus spell) { return learnedSpells.add(spell); }

    public void addBonusToRenaud(Bonus bonus) {
        bonusList.add(bonus);
        if (!bonus.getBonusType().equals(BonusType.BUFF)) {
            bonusDrawList.remove(bonus);
            learnedSpells.add(bonus);
        } 
        bonus.calcBuffOrValue(this);
    }

    public void nextRoom(){
        room++;
    }

    public void nextStage(){
        room = 0;
        stage++;
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    public void nextRoomTest(){
        int roomMax = 5; // on considère le max des room a 5 pour les test
        room++;
        while(room >= roomMax){
            room -= roomMax;
            nextStageTest();
        }
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    public void nextStageTest(){
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

    /**
     * Méthode surchargée pour la facilité des tests
     */
    public void giveExpTest(int exp) {
        expCurrent += exp;
        while(expCurrent >= expNeeded){
            expCurrent -= expNeeded;
            nextLevelTest();
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
        this.setCurrentHp(this.getHp());
    }

    public boolean isDead(){
        return this.getCurrentHp() <= 0;
    }

    /**
     * Méthode surchargée pour la facilité des tests
     */
    private void nextLevelTest() {
        level++;
        expNeeded *= 1.2;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + super.toString() + "[atk:" + atk + ", level:" + level + ", expNeeded:" + expNeeded
        + ", expCurrent:" + expCurrent + ", stage:" + stage + ", room:" + room + ", bonusList:" + bonusList.toString() 
        + ", bonusDrawList:" + bonusDrawList.toString() + ", learnedSpells:" + learnedSpells + "]";
    }
}
