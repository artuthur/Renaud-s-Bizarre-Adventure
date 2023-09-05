package main.effect;

import java.util.ArrayList;
import java.util.List;

import main.entity.Renaud;

public enum Bonus {
    LANCE_DE_BRIQUE("Lancé de brique", 0, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 1, 15),
    POING_BOUCHE("Poing-bouche", 0, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 3, 25),
    PTITE_BIERE("P'tite bière", 0, BonusType.SPELL, SpellType.HEALTH, UseType.HEAL, 2, 10),
    WRIGHT_KICK("Wright kick", 1, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 20),
    COUP_DE_BOULE("Coup de boule", 1, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 4, 50),
    BARQUETTE_DE_FRITTES("Barquette de frittes", 1, BonusType.SPELL, SpellType.HEALTH, UseType.HEAL, 2, 20),
    COCKTAIL_MOLOTOV("Cocktail molotov", 2, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 3, 50),
    BALAYETTE("Balayette", 2, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 5, 80),
    COCKTAIL_FRUITE("Cocktail fruité", 2, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 70),
    LANCE_DE_CHAISE("Lancé de chaise", 3, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 75),
    CONCERT_ASSOURDISSANT("Concert assourdissant", 3, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 3, 200),
    MENU_GIANT("Menu giant", 3, BonusType.SPELL, SpellType.ATTACK, UseType.HEAL, 4, 160),
    COUP_DE_GUITARE("Coup de guitare", 4, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 1, 100),
    REQUIEM_MACABRE("Requiem macabre", 4, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 4, 500),
    GRAND_CRU("Grand crû", 4, BonusType.SPELL, SpellType.ATTACK, UseType.DAMAGE, 2, 300);

    private String name;
    private int rarity;
    private BonusType type;
    private SpellType spellType;
    private UseType useType;
    private int cooldown;
    private int value;

    private Bonus(String name, int rarity, BonusType type, SpellType spellType, UseType useType, int cooldown, int value) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.spellType = spellType;
        this.useType = useType;
        this.cooldown = cooldown;
        this.value = value;
    }

    private Bonus(String name, int rarity, BonusType type, SpellType spellType, UseType useType, int value) {
        this(name, rarity, type, spellType, useType, 0, value);
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public BonusType getBonusType() {
        return type;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public UseType getUseType() {
        return useType;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getValue() {
        return value;
    }

    public List<Bonus> getBonusList() {
        ArrayList<Bonus> res = new ArrayList<Bonus>();
        for(Bonus b : values()) {
            res.add(b);
        }
        return res;
    }

    public int calcBuffOrValue(Renaud renaud) {
        int v = 0;
        if (spellType.equals(SpellType.ATTACK)) {
            v = (int) (value*(renaud.getAtk()*0.01));
            if (type.equals(BonusType.BUFF)) {
                renaud.setAtk(v);
            }
            return v;
        }
        if (spellType.equals(SpellType.DEFENSE)) {
            v = (int) (value*(renaud.getDef()*0.01));
            if (type.equals(BonusType.BUFF)) {
                renaud.setDef(v);
            }
            return v;
        }
        v = (int) (value*(renaud.getHp()*0.01));
            if (type.equals(BonusType.BUFF)) {
                renaud.setHp(v);
            }
        return v;
    }
}
