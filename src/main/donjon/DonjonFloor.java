package main.donjon;

import main.Mathf;
import main.exception.NullRoomPointerException;

public class DonjonFloor {
    public final static String ROOM_EXCEPTION = "Exception : La salle de l'étage demandée n'existe pas : ";
    public final static int ROOMS_MAX = 5;
    public final static int ROOMS_MIN = 3;
    public final static int ADVICE_CHANCE = 100/1; /* percentage */

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
        try {
            return getRoomException(room);
        } catch (NullRoomPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DonjonRoom getRoomException(int room) throws NullRoomPointerException{
        if(room < 0 || room >= rooms.length) throw new NullRoomPointerException(ROOM_EXCEPTION  + room);
        return rooms[room];
    }

    private void generateRooms(){
        int numberOfRooms = Mathf.random(ROOMS_MIN, ROOMS_MAX);
        rooms = new DonjonRoom[numberOfRooms];

        int advice = -1;
        if(Mathf.random(0, 100) < ADVICE_CHANCE) advice = Mathf.random(rooms.length - 2);

        for(int i = 0; i < rooms.length; i++){
            RoomType type;

            if(i == rooms.length - 1) type = RoomType.BOSS;
            else if(i == advice) type = RoomType.ADVICE;
            else type = RoomType.ENEMY;

            rooms[i] = new DonjonRoom(rooms.length - 1, i, type, theme);
        }
    }

    public void print(){
        print(0);
    }

    protected void print(int distance){
        for(int i = 0; i < distance; i++) System.out.print("\t");
        System.out.println(this);
        for(DonjonRoom dr : rooms) dr.print(distance + 1);
    }

    public String toString(){
        return getClass().getSimpleName() + "[theme:" + theme + ", roomsCount:" + getRoomsCount() + "]";
    }
}
