package main.donjon;

import main.Mathf;

public class DonjonFloor {
    public final static int ROOMS_MAX = 5;
    public final static int ROOMS_MIN = 3;
    public final static int ADVICE_CHANCE = 30; /* percentage */

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

    public DonjonRoom getRoom(int room){
        if(room >= 0 && room < rooms.length)
            return rooms[room];
        return null;
    }

    public void generateRooms(){
        int numberOfRooms = Mathf.random(ROOMS_MIN, ROOMS_MAX);
        rooms = new DonjonRoom[numberOfRooms];

        int advice = -1;
        if(Mathf.random(0, 100) < ADVICE_CHANCE) advice = Mathf.random(0, rooms.length - 2);

        for(int i = 0; i < rooms.length; i++){
            RoomType type;

            if(i == rooms.length - 1) type = RoomType.BOSS;
            else if(i == advice) type = RoomType.ADVICE;
            else type = RoomType.ENEMY;

            rooms[i] = new DonjonRoom(type);
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", roomsCount:" + getRoomsCount() + "]";
    }
}
