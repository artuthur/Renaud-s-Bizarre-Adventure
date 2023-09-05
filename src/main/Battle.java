package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import main.bestiary.Bestiary;
import main.entity.Monster;
import main.entity.Renaud;

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
        StringBuilder sb = new StringBuilder();
        sb.append(this.mob.name());
        sb.append(" vous attaque avec ");
        sb.append(foe.getSpell().name());
        sb.append(".\nVous perdez ");
        int damage = (int) (foe.getSpell().getAmount() - (this.player.getDef() * DAMAGE_REDUCE));
        sb.append(damage);
        sb.append(" points de vie.");
        player.setCurrentHp(player.getCurrentHp() - damage);
        System.out.println(sb.toString());
    }

    public void bossTurn() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mob.name());
        sb.append(" vous attaque avec ");
        sb.append(foe.getSpell().name());
        sb.append(".\nVous perdez ");
        int damage = calculateDamage(foe.getSpell().getAmount(), player.getDef());
        sb.append(damage);
        sb.append(" points de vie.");
        player.setCurrentHp(player.getCurrentHp() - damage);
        System.out.println(sb.toString());
    }

    public void renaudTurn() {
        StringBuilder sb = new StringBuilder();
        System.out.println("C'est votre tour.\nQue souhaitez-vous faire ?\n1. Attaque de base.\n2. Sorts.\n: ");
        String choix = Game.readStringNotNull();
        if (choix.equals("1")) {
            int damage = calculateDamage(player.getAtk(), foe.getDef());
            sb.append("Vous utilisez une attaque normale et infligez ");
            sb.append(damage);
            sb.append(" dégât à ");
            sb.append(this.mob.name());
            System.out.println(sb.toString());
            foe.setCurrentHp(foe.getCurrentHp() - damage);
        }


    }

    public int calculateDamage(int amount, int def) {
        int damage = (int) (amount - (def * DAMAGE_REDUCE));
        if (damage <= 0 ) damage = 1;
        return damage;
    }

    public void applyDamage(int amount, int def) {
        int newHp;
        if (isRenaudTurn) {
            newHp = foe.getCurrentHp() - calculateDamage(amount, def);
            if (newHp < 0) newHp = 0;
            foe.setHp(newHp);
        }
        else {
            newHp = player.getCurrentHp() - calculateDamage(amount, def);
            if (newHp < 0) newHp = 0;
            player.setHp(newHp);
        }
    }

    public static void main(String[] args) {
        Battle bt = new Battle(new Renaud(), Bestiary.CRS);
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
            Game.clearScreen();
            System.out.println("Foe : " + bt.foe.getCurrentHp() + " | Player : " + bt.player.getCurrentHp());
        }
    }
}
