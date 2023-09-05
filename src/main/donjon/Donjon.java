package main.donjon;

import java.util.ArrayList;
import java.util.List;

import main.Game;

public class Donjon {
    private DonjonFloor[] floors;

    public Donjon(){
        generateFloors();
    }

    public int getFloorsCount(){
        return floors.length;
    }
    
    public int getNumberOfRooms(int stage){
        if(stage > 0 && stage < floors.length){
            return 1;   
        }
        return 0;
    }

    public void generateFloors(){
        Theme[] themes = Theme.values();

        floors = new DonjonFloor[themes.length];

        List<Theme> currentThemes = new ArrayList<>();
        for(Theme t : themes){
            if(t != Theme.FINALE){
                currentThemes.add(t);
            }
        }

        for(int i = 0; i < themes.length; i++){
            Theme theme;
            if(i != themes.length - 1){
                theme = currentThemes.get(Game.random(0, currentThemes.size() - 1));
                currentThemes.remove(theme);
            }else{
                theme = Theme.FINALE;
            }
            floors[i] = new DonjonFloor(theme);
        }
    }

    public void print(){
        System.out.println(this);
        for(DonjonFloor df : floors){
            System.out.println("\t" + df);
            for(DonjonRoom dr : df.getRooms()){
                System.out.println("\t\t" + dr);
            }
        }
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[floorsCount:" + getFloorsCount() + "]";
    }
}