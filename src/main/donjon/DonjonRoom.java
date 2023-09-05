package main.donjon;

import java.util.List;

import main.Advice;
import main.bestiary.Bestiary;

public class DonjonRoom {
    private RoomType type;
    private Theme theme;
    private Bestiary mob;
    private Advice advice;

    public DonjonRoom(RoomType type, Theme theme){
        this.type = type;
        this.theme = theme;
        generateRoom();
    }

    public RoomType getType(){
        return type;
    }

    public Theme getTheme(){
        return theme;
    }

    public void generateRoom(){
        List<Bestiary> mobs = Bestiary.getMobs(theme);
        switch(type){
            case ADVICE:
                advice = Advice.getRandom();
                break;
            case ENEMY:
                mob = Bestiary.getMobs(mobs, false);
                break;
            case BOSS:
                mob = Bestiary.getMobs(mobs, true);
                break;
        }
    }

    public void print(){
        print(0);
    }

    protected void print(int distance){
        for(int i = 0; i < distance; i++) System.out.print("\t");
        System.out.println(this);
    }

    public String toString(){
        return getClass().getSimpleName() + "[type:" + type + ", theme:" + theme + ", mob:" + mob + ", advice:" + advice + "]";
    }
}