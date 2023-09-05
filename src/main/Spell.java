package main;

public enum Spell {
    SPELLTEST(SpellType.ATTACK, 20);

    private SpellType type;
    private int amount;

    private Spell(SpellType type, int amount){
        this.type = type;
        this.amount = amount;
    }

    public SpellType getType(){
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public String toString(){
        return getClass().getSimpleName() + "[type:" + type + ", amount:" + amount + "]";
    }
}
