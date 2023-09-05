package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.bestiary.Bestiary;
import main.effect.Bonus;
import main.effect.BonusType;
import main.effect.Spell;
import main.effect.UseType;
import main.entity.Monster;
import main.entity.Renaud;
import main.entity.Entity;

public class Battle {

    private final static double BASE_CHANCE = 0.5;
    private final static double DAMAGE_REDUCE = 0.1;

    private Map<Bonus, Integer> spellInCooldown;

    private Renaud player;
    private Bestiary mob;
    private Monster foe;

    private boolean isRenaudTurn;

    public Battle(Renaud player, Bestiary mob) {
        this.player = player;
        this.mob = mob;
        this.foe = new Monster(mob);
        this.spellInCooldown = new HashMap<Bonus, Integer>();
    }
    
    public void speedtie() {
        double chance = BASE_CHANCE + (this.player.getLevel() * 0.1);
        double randomNum = Mathf.random(0, 1);
        if (randomNum <= chance) {
            this.isRenaudTurn = true;
            System.out.println("Renaud commence en premier.");
        }
        else {
            this.isRenaudTurn = false;
            System.out.println(this.mob.name() + " commence en premier.");
        }
    }

    public void changeTurn() {
        this.isRenaudTurn = !isRenaudTurn;
    }
    
    public void foeTurn() {
        Game.clearScreen();
        System.out.println("\nPlayer : " + player.getCurrentHp() + " | Foe : " + foe.getCurrentHp());
        Spell spellUse;
        if (this.mob.isBoss()) {
            if (Mathf.random(0, 1) <= 0.6) {
                spellUse = this.mob.getFirstSpell();
            }
            else {
                spellUse = this.mob.getSecondSpell();
            }
        }
        else {
            spellUse = this.mob.getFirstSpell();
        }
        StringBuilder sb = new StringBuilder();
        int damage = calculatePhysicalDamage(spellUse.getAmount(), player.getDef());
        sb.append(this.mob.getName());
        sb.append(" vous attaque avec ");
        sb.append(spellUse.getName());
        sb.append(".\nVous perdez ");
        sb.append(damage);
        sb.append(" points de vie.");
        applyDamage(player, damage);
        System.out.println(sb.toString());
    }

    public void renaudTurn() {
        Game.clearScreen();
        System.out.println("\nPlayer : " + player.getCurrentHp() + " | Foe : " + foe.getCurrentHp());
        StringBuilder sb = new StringBuilder();
        int damage = 0;
        Bonus b = null;
        System.out.println("C'est votre tour.\nQue souhaitez-vous faire ?\n1. Attaque de base.\n2. Sorts.\n: ");
        String choix = Game.readStringNotNull();
        if (choix.equals("1")) {
            damage = calculatePhysicalDamage(player.getAtk(), foe.getDef());
            sb.append("Vous utilisez une attaque normale et infligez ");
            sb.append(damage);
            sb.append(" dégât à ");
            sb.append(this.mob.getName());
            applyDamage(foe, damage);
            System.out.println(sb.toString());
        }
        else if (choix.equals("2")) {
            if (player.getLearnedSpells().isEmpty()) {
                renaudTurn();
            }
            else {
                boolean spellCast = false;
                do {
                    b = choiceSpell();
                    if (!spellInCooldown.containsKey(b)) {
                        if (b.getUseType() == UseType.DAMAGE) {
                            if (b.getBonusType() == BonusType.SPELL) {
                                damage = b.getValue();
                            }
                            else if (b.getBonusType() == BonusType.SPELL_SCALING) {
                                damage = b.calcBuffOrValue(player);
                            }
                            sb.append("Vous utilisez ");
                            sb.append(b.getName());
                            sb.append(" et infligez ");
                            sb.append(damage);
                            sb.append(" dégât à ");
                            sb.append(this.mob.getName());
                            applyDamage(foe, damage);
                            System.out.println(sb.toString());
                        }
                        else {
                            int heal = 0;
                            if (b.getBonusType() == BonusType.SPELL) {
                                heal = b.getValue();
                            }
                            else if (b.getBonusType() == BonusType.SPELL_SCALING) {
                                heal = b.calcBuffOrValue(player);
                            }
                            sb.append("Vous utilisez ");
                            sb.append(b.getName());
                            sb.append(" et récupérez ");
                            sb.append(heal);
                            sb.append(" points de vie.");
                            player.setCurrentHp(player.getCurrentHp() + heal);
                            System.out.println(sb.toString());
                        }
                        spellCast = true;
                    }
                    else {
                        renaudTurn();
                    }
                } while (!spellCast);       
            }
        }
        else {
            renaudTurn();
        }
        updateCooldown();
        clearCooldown();
        if (b != null) {
            spellInCooldown.put(b, Integer.valueOf(b.getCooldown()));
        }

    }

    public int calculatePhysicalDamage(int amount, int def) {
        int damage = (int) (amount - (def * DAMAGE_REDUCE));
        if (damage <= 0 ) damage = 1;
        return damage;
    }

    public void applyDamage(Entity defender, int damage) {
        int newHp = defender.getCurrentHp() - damage;
        if (newHp < 0) newHp = 0;
        defender.setCurrentHp(newHp);
    }

    public Bonus choiceSpell() {
        int i = 1;
        for (Bonus b : player.getLearnedSpells()) {
            System.out.print(i + ". " + b.getName() + " (");
            if (spellInCooldown.containsKey(b)) {
                System.out.print(spellInCooldown.get(b) + " tours");
            }
            else {
                System.out.print("Disponible");
            }
            System.out.println(")");
            i++;
            
        }
        int choix = Integer.parseInt(Game.readStringNotNull());
        try {
            return player.getLearnedSpells().get(choix-1);
        }
        catch (Exception e) {
            return choiceSpell();
        }
    }

    public void updateCooldown() {
        for (Entry<Bonus, Integer> e : spellInCooldown.entrySet()) {
            spellInCooldown.put((Bonus) e.getKey(), (Integer.valueOf(((Integer) e.getValue()).intValue() - 1)));
        }
    }

    public void clearCooldown() {
        for (Entry<Bonus, Integer> e : spellInCooldown.entrySet()) {
            if (((Integer) e.getValue()).intValue() == 0) {
                spellInCooldown.remove((Bonus) e.getKey());
            }
        }
    }

    public Renaud getPlayer() {
        return player;
    }

    public Bestiary getMob() {
        return mob;
    }

    public Monster getFoe() {
        return foe;
    }

    public static void main(String[] args) {
        Battle bt = new Battle(new Renaud(), Bestiary.CRS);
        bt.speedtie();
        bt.player.addBonusToRenaud(Bonus.LANCE_DE_BRIQUE);
        bt.player.addBonusToRenaud(Bonus.PTITE_BIERE);
        bt.player.addBonusToRenaud(Bonus.HARD_METAL);
        while (bt.player.getCurrentHp() > 0 && bt.foe.getCurrentHp() > 0) {
            if (bt.isRenaudTurn) {
                bt.renaudTurn();
                Game.readStringNotNull();
            }
            else {
                bt.foeTurn();
                Game.readStringNotNull();
            }
            bt.changeTurn();
        }
        if (!bt.isRenaudTurn) {
            System.out.println("Vous avez gagné ggez!");
            bt.player.giveExp(40);
            bt.player.setRoom(bt.player.getRoom()+1);
        }
        else {
            System.out.println("Il est sad le héros un peu.");
        }
    }
}
