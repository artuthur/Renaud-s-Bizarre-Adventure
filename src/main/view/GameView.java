package main.view;

import main.Battle;
import main.Game;
import main.donjon.Donjon;
import main.donjon.DonjonGenerator;
import main.donjon.DonjonRoom;
import main.entity.Renaud;

public class GameView {
    private DonjonGenerator donjonGenerator;
    private Renaud player;
    private static RenaudView renaudView;

    public GameView(DonjonGenerator donjonGenerator, Renaud player){
        this.donjonGenerator = donjonGenerator;
        this.player = player;
    }

    public DonjonGenerator getDonjonGenerator(){
        return donjonGenerator;
    }

    public Renaud getPlayer(){
        return player;
    }

    public static void start(){
        Donjon donjon = new Donjon();
        Renaud player = new Renaud(donjon);
        DonjonGenerator donjonGenerator = new DonjonGenerator(donjon, player);
        GameView gameView = new GameView(donjonGenerator, player);
        GameView.renaudView = new RenaudView(player);

        DialogueView.startGame();
        do{
            Game.clearScreen();
            renaudView.printStats();
            donjonGenerator.drawDonjon();
            Game.pressToContinue();
            gameView.checkPlayerCase();
        }while(!gameView.isFinish());
    }

    public boolean isFinish(){
        if(player.isDead()){
            playerDead();
            return true;
        }
        if(player.getStage() > donjonGenerator.getDonjon().getFloorsCount()){
            playerWin();
            return true;
        }
        return false;
    }

    public void playerDead(){
        DialogueView.playerDead();
    }

    public void playerWin(){
        DialogueView.playerWin();
    }

    public void checkPlayerCase(){
        DonjonRoom donjonRoom = donjonGenerator.getCurrentRoom();
        if(donjonRoom == null) return;
        if(donjonRoom.getMob() != null){
            Battle bt = new Battle(player, donjonRoom.getMob(), GameView.renaudView);
            bt.battle();
        }
        if(donjonRoom.getAdvice() != null){
            AdviceView.load(donjonRoom.getAdvice());
            player.nextRoom();
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[donjonGenerator:" + donjonGenerator + ", player:" + player + "]";
    }
}
