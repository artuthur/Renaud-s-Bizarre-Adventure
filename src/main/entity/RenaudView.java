package main.entity;

public class RenaudView {
    private static Renaud player;

    public RenaudView(Renaud player) {
        this.player = player;
    }

    public void printStats() {
        String txt = '|' + stageRoomString() + '|' + lvlString() + '|' + expString() 
            + '|' + hpString() + '|' + atkString() + '|' + defString() + '|';
        System.out.print('╭');
        for (int i = 0; i < txt.length()-2; i++) {
            System.out.print('\u2500');
        }
        System.out.println('╮');
        System.out.println(txt);
        System.out.print('╰');
        for (int i = 0; i < txt.length()-2; i++) {
            System.out.print('\u2500');
        }
        System.out.println('╯');
    }

    public void printBattleStats() {
        String txt = '|' + stageRoomString() + '|' + lvlString() + '|' + expString() 
            + '|' + atkString() + '|' + defString() + '|';
        System.out.print('╭');
        for (int i = 0; i < txt.length()-2; i++) {
            System.out.print('\u2500');
        }
        System.out.println('╮');
        System.out.println(txt);
        System.out.print('╰');
        for (int i = 0; i < txt.length()-2; i++) {
            System.out.print('\u2500');
        }
        System.out.println('╯');
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
        return "Stage:" + (player.getStage()+1) + "-" + (player.getRoom()+1);
    }
}
