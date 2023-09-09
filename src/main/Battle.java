package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import main.bestiary.Bestiary;
import main.donjon.Donjon;
import main.effect.Bonus;
import main.effect.BonusType;
import main.effect.Spell;
import main.effect.UseType;
import main.entity.Monster;
import main.entity.Renaud;
import main.view.BattleView;
import main.view.RenaudView;
import main.entity.Entity;

public class Battle {
    private final static double BASE_CHANCE = 0.5;
    private final static int EXP_GAIN = 50;

    private Renaud player;
    private Bestiary mob;
    private Monster foe;

    private boolean isRenaudTurn;

    public Battle(Renaud player, Bestiary mob) {
        this.player = player;
        this.mob = mob;
        this.foe = new Monster(mob);
        foe.stageScale(player.getStage());
        foe.setCurrentHp(foe.getHp());
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
            System.out.println(foe.getMob().getName() + " commence en premier.");
        }
        Game.pressToContinue();
    }

    public void changeTurn() {
        this.isRenaudTurn = !isRenaudTurn;
    }
    
    public void foeTurn() {
        Game.clearScreen();
        RenaudView.printBattleStats();
        BattleView.afficheSprites(this);
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
        damage = scaleDamage(damage);
        sb.append(foe.getMob().getName());
        sb.append(" vous attaque avec ");
        sb.append(spellUse.getName());
        sb.append(".\nVous perdez ");
        sb.append(damage);
        sb.append(" points de vie.");
        applyDamage(player, damage);
        System.out.println(sb.toString());
        Game.pressToContinue();
    }

    public void renaudTurn() {
        Game.clearScreen();
        RenaudView.printBattleStats();
        BattleView.afficheSprites(this);
        StringBuilder sb = new StringBuilder();
        int damage = 0;
        Bonus b = null;
        System.out.println("C'est votre tour.\nQue souhaitez-vous faire ?\n\n1. Attaque de base.\n2. Sorts.\n");
        System.out.print("Sélectionnez une action : ");
        String choix = Game.readStringNotNull();
        if (choix.equals("1")) {
            damage = calculatePhysicalDamage(player.getAtk(), foe.getDef());
            sb.append("Vous utilisez une attaque normale et infligez ");
            sb.append(damage);
            sb.append(" dégât à ");
            sb.append(foe.getMob().getName());
            applyDamage(foe, damage);
            System.out.println(sb.toString());
            Game.pressToContinue();
        }
        else if (choix.equals("2")) {
            if (player.getLearnedSpells().isEmpty()) {
                System.out.println("Vous n'avez pas de sorts.");
                Game.pressToContinue();
                renaudTurn();
            }
            else {
                sb = new StringBuilder();
                b = choiceSpell();
                if (b != null) {
                    if (b.getUseType() == UseType.DAMAGE) {
                        applyDamageSpell(b);
                    }
                    else {
                        applyHealingSpell(b);
                    }
                }
                else {
                    renaudTurn();
                } 
            }
        }
        else {
            renaudTurn();
        }
        updateCooldown();
        if (b != null) {
            Donjon.spellInCD.put(b, Integer.valueOf(b.getCooldown()));
        }

    }

    public int calculatePhysicalDamage(int amount, int def) {
        int damage = (int) (amount * (100.0/(100.0+def)));
        if (damage <= 0 ) damage = 1;
        return damage;
    }

    public void applyDamage(Entity defender, int damage) {
        int newHp = defender.getCurrentHp() - damage;
        if (newHp < 0) newHp = 0;
        defender.setCurrentHp(newHp);
    }

    public int scaleDamage(int damage) {
        return (int)(damage + damage*(0.2*player.getStage()));
    }

    public Bonus choiceSpell() {
        Game.clearScreen();
        RenaudView.printBattleStats();
        BattleView.afficheSprites(this);
        int i = 0;
        System.out.println("0. Retour");
        for (Bonus b : player.getLearnedSpells()) {
            System.out.print((i+1) + ". " + b.getName() + " (");
            if (spellIsInCD(b)) {
                System.out.print(Donjon.spellInCD.get(b) + " tours");
            }
            else {
                System.out.print("Disponible");
            }
            System.out.println(")");
            i++; 
        }
        try {
            Bonus b = null;
            int choix = Integer.parseInt(Game.readStringNotNull());
            if (choix == 0) {
                return b;
            }
            b = player.getLearnedSpells().get(choix-1);
            if (spellIsInCD(b)) {
                return null;
            }
            return b;
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean applyDamageSpell(Bonus b) {
        int damage = 0;
        StringBuilder sb = new StringBuilder();
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
        sb.append(foe.getMob().getName());
        applyDamage(foe, damage);
        System.out.println(sb.toString());
        Game.pressToContinue();
        return true;
    }

    public boolean applyHealingSpell(Bonus b) {
        int heal = 0;
        StringBuilder sb = new StringBuilder();
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
        int newHp = player.getCurrentHp() + heal;
        if (newHp > player.getHp()) newHp = player.getHp();
        player.setCurrentHp(newHp);
        System.out.println(sb.toString());
        Game.pressToContinue();
        return true;
    }

    public void updateCooldown() {
        List<Bonus> finishedCD = new ArrayList<Bonus>();
        for (Entry<Bonus, Integer> e : Donjon.spellInCD.entrySet()) {
            Bonus b = (Bonus) e.getKey();
            Integer i = (Integer) e.getValue();
            Donjon.spellInCD.put(b, (i-1));
            if ((i-1) == 0) finishedCD.add(b);
        }
        for (Bonus b : finishedCD) {
            Donjon.spellInCD.remove(b);
        }
    }

    public boolean spellIsInCD(Bonus b) {
        return Donjon.spellInCD.containsKey(b);
    }

    public void battle() {
        BattleView.afficheBattle();
        RenaudView.printBattleStats();
        BattleView.afficheSprites(this);
        speedtie();
        while (player.getCurrentHp() > 0 && foe.getCurrentHp() > 0) {
            if (isRenaudTurn) renaudTurn();
            else foeTurn();
            changeTurn();
        }
        if (foe.getCurrentHp() <= 0) {
            if (foe.isBoss()) {
                player.nextLevel();
            }
            else {
                player.giveExp(EXP_GAIN);
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
}
