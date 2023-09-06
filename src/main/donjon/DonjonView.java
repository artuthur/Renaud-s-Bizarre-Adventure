package main.donjon;

import main.Battle;
import main.Game;
import main.entity.Renaud;

public class DonjonView {
    private DonjonGenerator donjonGenerator;
    private Renaud player;

    public DonjonView(DonjonGenerator donjonGenerator, Renaud player){
        this.donjonGenerator = donjonGenerator;
        this.player = player;
    }

    public DonjonGenerator getDonjonGenerator(){
        return donjonGenerator;
    }

    public Renaud getPlayer(){
        return player;
    }

    public void start(){
        do{
            Game.clearScreen();
            System.out.println("Etage actuel : " + (player.getStage() + 1));
            donjonGenerator.drawDonjon();
            Game.pressToContinue();
            checkPlayerCase();
        }while(!isFinish());
    }

    public boolean isFinish(){
        if(player.getStage() > donjonGenerator.getDonjon().getFloorsCount()){
            System.out.println("Il n'y a plus d'étage !");
            return true;
        }
        return false;
    }

    public void checkPlayerCase(){
        DonjonRoom donjonRoom = donjonGenerator.getCurrentRoom();
        if(donjonRoom == null) return;
        if(donjonRoom.getMob() != null){
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
