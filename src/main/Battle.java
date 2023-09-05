package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import main.bestiary.Bestiary;
import main.effect.Spell;
import main.entity.Monster;
import main.entity.Renaud;
import main.entity.Boss;
import main.entity.Entity;

public class Battle {

    private final static double BASE_CHANCE = 0.5;
    private final static double DAMAGE_REDUCE = 0.1;

    private Renaud player;
    private Bestiary mob;
    private Monster foe;

    private boolean isRenaudTurn;

    public Battle(Renaud player, Bestiary mob) {
        this.player = player;
        this.mob = mob;
        this.foe = new Monster(mob);
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
        System.out.println("C'est votre tour.\nQue souhaitez-vous faire ?\n1. Attaque de base.\n2. Sorts.\n: ");
        String choix = Game.readStringNotNull();
        if (choix.equals("1")) {
            int damage = calculatePhysicalDamage(player.getAtk(), foe.getDef());
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
        }
        else {
            renaudTurn();
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

    public static void main(String[] args) {
        Battle bt = new Battle(new Renaud(), Bestiary.JEAN_MERLIN_BLANQUER);
        bt.speedtie();
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
        }
        else {
            System.out.println("Il est sad le héros un peu.");
        }
    }
}
