package main.view;

import main.Battle;
import main.Game;
import main.donjon.Donjon;
import main.donjon.DonjonGenerator;
import main.donjon.DonjonRoom;
import main.entity.Renaud;

public class GameView {
    public final static char CHAR_UP_LEFT = '\u256F';
    public final static char CHAR_UP_RIGHT = '\u2570';
    public final static char CHAR_DOWN_LEFT = '\u256D';
    public final static char CHAR_DOWN_RIGHT = '\u256D';
    public final static char CHAR_VERTICAL = '\u2502';
    public final static char CHAR_HORIZONTAL = '\u2500';
    public final static char CHAR_WAY = '\u2550';
    
    private DonjonGenerator donjonGenerator;
    private Renaud player;

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
        RenaudView.player = player;
        
        DialogueView.startGame();
        DialogueView.startStage(player.getCurrentTheme());
        
        do{
            Game.clearScreen();
            RenaudView.printPlayerStats();
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
            if(donjonRoom.getMob().isBoss()){
                DialogueView.startBoss(donjonRoom.getMob());
            }
            Battle bt = new Battle(player, donjonRoom.getMob());
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
