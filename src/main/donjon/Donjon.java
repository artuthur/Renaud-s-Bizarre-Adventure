package main.donjon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Mathf;
import main.effect.Bonus;
import main.exception.NullFloorPointerException;
import main.exception.NullRoomPointerException;

public class Donjon {
    public final static String FLOOR_EXCEPTION = "Exception : L'étage demandé n'existe pas : ";
    public static Map<Bonus, Integer> spellInCD;
    
    private DonjonFloor[] floors;

    public Donjon(){
        generateFloors();
        spellInCD = new HashMap<Bonus, Integer>();
    }

    public DonjonFloor[] getFloors(){
        return floors;
    }

    public int getFloorsCount(){
        return floors.length;
    }

    public int getRoomsCount(int stage){
        if(stage >= 0 && stage < floors.length)
            return floors[stage].getRoomsCount();
        return -1;
    }

    public DonjonFloor getFloor(int stage){
        try {
            return getFloorException(stage);
        } catch (NullFloorPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DonjonFloor getFloorException(int stage) throws NullFloorPointerException{
        if(stage < 0 || stage >= floors.length) throw new NullFloorPointerException(FLOOR_EXCEPTION + stage);
        return floors[stage];
    }

    public DonjonRoom getRoom(int stage, int room){
        try{
            return getRoomException(stage, room);
        }catch(NullFloorPointerException | NullRoomPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DonjonRoom getRoomException(int stage, int room) throws NullFloorPointerException, NullRoomPointerException{
        DonjonFloor floor = getFloor(stage);
        if(floor == null) throw new NullFloorPointerException(FLOOR_EXCEPTION + stage);
        return floor.getRoomException(room);
    }

    public Theme getTheme(int stage){
        DonjonFloor dj = getFloor(stage);
        if(dj == null) return null;
        return dj.getTheme();
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
            if(i == themes.length - 1){
                theme = Theme.FINALE;
            }else{
                theme = currentThemes.get(Mathf.random(currentThemes.size() - 1));
                currentThemes.remove(theme);
            }
            floors[i] = new DonjonFloor(theme);
        }
    }

    protected void print(){
        System.out.println(this);
        for(DonjonFloor df : floors) df.print(1);
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[floorsCount:" + getFloorsCount() + "]";
    }
}