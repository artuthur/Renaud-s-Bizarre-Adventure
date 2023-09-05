package main.donjon;

public class DonjonRoom {
    private RoomType type;

    public DonjonRoom(RoomType type){
        this.type = type;
    }

    public RoomType getType(){
        return type;
    }

    public String toString(){
        return getClass().getSimpleName() + "[type:" + type + "]";
    }
}