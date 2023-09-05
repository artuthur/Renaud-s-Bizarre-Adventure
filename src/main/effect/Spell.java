package main.effect;

public enum Spell {
    NON_LIEU("Non lieu", SpellType.ATTACK, 30),
    DECENTE_DE_GARDE("Décente de garde", SpellType.ATTACK, 50),
    COUP_DE_MATRAQUE("Coup de matraque", SpellType.ATTACK, 10),
    FLASH_BALL("Flash Ball", SpellType.ATTACK, 30),
    BAVURE("Bavure", SpellType.ATTACK, 40),
    MORSURE("Morsure", SpellType.ATTACK, 30),
    DEGRADATION_MENTAL("Dégradation mentale", SpellType.ATTACK, 40),
    BURNOUT("Burnout", SpellType.ATTACK, 50),
    COUP_DE_REGLE_MAGIC("Coup de règle magique", SpellType.ATTACK, 25),
    DEVOIR_MAISON("Devoir maison", SpellType.ATTACK, 40),
    ESSOUFFLEMENT("Éssoufflement", SpellType.ATTACK, 5),
    TOILE_ETHERIQUE("Toile étherique", SpellType.ATTACK, 25),
    BARRAGE("Barrage", SpellType.ATTACK, 40),
    FRAPPE_INSOUMISE("Frappe Insoumise", SpellType.ATTACK, 20),
    EXPLOSION_MARCANIQUE("Explosion Marcanique", SpellType.ATTACK, 20),
    PILLAGE("Pillage", SpellType.ATTACK, 30),
    HARCELEMENT_CIBLER("Harcelement cibler", SpellType.ATTACK, 10),
    GUILLOTINE("Guillotine", SpellType.ATTACK, 35),
    ASMODEUS("Asmodeus, Démone de la luxure", SpellType.ATTACK, 50),
    ETREINTE_VAMPIRIQUE("Étreinte vampirique", SpellType.ATTACK, 20),
    ENLACEMENT_EROTIQUE("Enlacement érotique", SpellType.ATTACK, 20),
    DEUX_BON_ARGUMENT("Deux 'BON' arguments", SpellType.ATTACK, 30),
    DRAINAGE_SANGUIN("Drainage sanguin", SpellType.ATTACK, 35),
    CHARGE_INERTE("Charge inerte", SpellType.ATTACK, 1),
    MORNING_STAR("Morning Star", SpellType.ATTACK, 66),
    CONFINEMENT("Confinement", SpellType.ATTACK, 93);

    private String name;
    private SpellType type;
    private int amount;

    private Spell(String name, SpellType type, int amount){
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public String getName(){
        return name;
    }

    public SpellType getType(){
        return type;
    }

    public int getAmount(){
        return amount;
    }

    public String toString(){
        return getClass().getSimpleName() + "[name:" + name + ", type:" + type + ", amount:" + amount + "]";
    }
}
