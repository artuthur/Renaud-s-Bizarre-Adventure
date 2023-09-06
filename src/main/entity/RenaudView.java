package main.entity;

import main.Game;

public class RenaudView {
    private static Renaud player;

    public RenaudView(Renaud player) {
        this.player = player;
    }

    public void printStats() {
        String txt = "|" + stageRoomString() + "|" + lvlString() + "|" + expString() 
            + "|" + hpString() + "|" + atkString() + "|" + defString() + "|";
        System.out.println(txt);
    }

    public String lvlString() {
        return "Niveau:" + player.getLevel();
    }
    public String expString() {
        return "Expérience:" + player.getExpCurrent() + "/" + player.getExpNeeded();
    }
    public String hpString() {
        return "Points de vie:" + player.getCurrentHp() + "/" + player.getHp();
    }
    public String atkString() {
        return "Attaque:" + player.getAtk();
    }
    public String defString() {
        return "Défense:" + player.getDef();
    }
    public String stageRoomString() {
        return "Stage:" + player.getStage()+1 + "-" + player.getRoom()+1;
    }

    public static void main(String[] args) {
        Game.clearScreen();
        RenaudView rv = new RenaudView(new Renaud());
        rv.printStats();
    }
}
