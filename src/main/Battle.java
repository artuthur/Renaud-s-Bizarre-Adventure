package main;

import java.util.Random;
import java.util.random.RandomGenerator;

import javax.swing.text.html.parser.Entity;

import main.bestiary.Bestiary;
import main.entity.Monster;
import main.entity.Renaud;

public class Battle {

    private final static double BASECHANCE = 0.5;

    private Renaud player;
    private Bestiary mob;
    private double chance;

    private boolean isRenaudTurn;

    public Battle(Renaud player, Bestiary mob) {
        this.player = player;
        this.mob = mob;
        this.chance = Battle.BASECHANCE + (player.getLevel() * 0.1);
    }
    
    public void speedtie() {
        RandomGenerator rg = new Random();
        double chance = BASECHANCE + (this.player.getLevel() * 0.1);
        double randomNum = rg.nextDouble(0, 1);
        System.out.println(randomNum);
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
        Monster foe = new Monster()

    }

    public static void main(String[] args) {
        Battle bt = new Battle(new Renaud(), Bestiary.CRS);
        bt.speedtie();
    }
}
