package main.donjon;

import main.Mathf;

public class DonjonFloor {
    public final static int ROOMS_MAX = 5;
    public final static int ROOMS_MIN = 3;

    private Theme theme;
    private DonjonRoom[] rooms;

    public DonjonFloor(Theme theme){
        this.theme = theme;
        generateRooms();
    }
    
    public Theme getTheme(){
        return theme;
    }

    public DonjonRoom[] getRooms(){
        return rooms;
    }

    public int getRoomsCount(){
        return rooms.length;
    }

    public void generateRooms(){
        int numberOfRooms = Mathf.random(ROOMS_MIN, ROOMS_MAX);
        rooms = new DonjonRoom[numberOfRooms];

        for(int i = 0; i < rooms.length; i++){
            RoomType type;

            if(i != rooms.length - 1){
                type = RoomType.ENEMY;
            }else{
                type = RoomType.BOSS;
            }

            rooms[i] = new DonjonRoom(type);
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", roomsCount:" + getRoomsCount() + "]";
    }
}
