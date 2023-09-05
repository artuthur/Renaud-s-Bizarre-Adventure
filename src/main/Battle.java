package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.random.RandomGenerator;

import javax.swing.text.html.parser.Entity;

import main.bestiary.Bestiary;
import main.entity.Monster;
import main.entity.Renaud;

public class Battle {

    private final static double BASE_CHANCE = 0.5;
    private final static double DOMAGE_REDUCE = 0.1;

    private Renaud player;
    private Bestiary mob;
    private Monster foe;
    private double chance;

    private boolean isRenaudTurn;

    public Battle(Renaud player, Bestiary mob) {
        this.player = player;
        this.foe = new Monster(this.mob);
        this.chance = Battle.BASE_CHANCE + (player.getLevel() * 0.1);
    }
    
    public void speedtie() {
        RandomGenerator rg = new Random();
        double chance = BASE_CHANCE + (this.player.getLevel() * 0.1);
        double randomNum = rg.nextDouble(0, 1);
        if (randomNum <= chance) {
            this.isRenaudTurn = true;
            System.out.println("Renaud commence en premier.");
        }
        else {
            this.isRenaudTurn = false;
            System.out.println(this.mob.name() + " commence en premier.");
        }
    }

    public void foeTurn() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mob.name());
        sb.append(" attaque Renaud avec ");
        sb.append(foe.getSpell().name());
        sb.append(".\nIl perd ");
        int damage = (int) (foe.getSpell().getAmount() - (this.player.getDef() * DOMAGE_REDUCE));
        sb.append(damage);
        sb.append(" points de vie.");
        player.setHp(player.getHp() - damage);
        System.out.println(sb.toString());
    }

    public void bossTurn() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mob.name());
        sb.append(" vous attaque avec ");
        sb.append(foe.getSpell().name());
        sb.append(".\nVous perdez ");
        int damage = (int) (foe.getSpell().getAmount() - (this.player.getDef() * DOMAGE_REDUCE));
        sb.append(damage);
        sb.append(" points de vie.");
        player.setHp(player.getHp() - damage);
        System.out.println(sb.toString());
    }
    
    public void changeTurn() {
        this.isRenaudTurn = !isRenaudTurn;
    }

    public void renaudTurn() {
        StringBuilder sb = new StringBuilder();
        sb.append("C'est votre tour.\nQue souhaitez-vous faire ?\n1. Attaque de base.\n2. Sorts.\n: ");
        String choix = read();
        if (choix.equals("1")) {

        }


    }

    public static String read(){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            return br.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Battle bt = new Battle(new Renaud(), Bestiary.CRS);
        bt.speedtie();
        if (bt.isRenaudTurn) {
            //TODO
        }
        else {
            bt.foeTurn();
        }
        bt.changeTurn();
        if (bt.isRenaudTurn) {
            //TODO
        }
        else {
            bt.foeTurn();
        }
    }
}
