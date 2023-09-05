package main.donjon;

public class DonjonGenerator {
    public final static int ROOM_WIDTH = 20;
    public final static int ROOM_HEIGHT = 20;

    private Donjon donjon;
    private int currentStage;
    private int currentRoom;

    public DonjonGenerator(Donjon donjon){
        this.donjon = donjon;
    }

    public Donjon getDonjon(){
        return donjon;
    }

    public char[][] loadCurrentRoom(){
        return loadRoom(currentRoom);
    }

    public char[][] loadRoom(int room){
        int roomsCount = donjon.getNumberOfRooms(currentStage);
        System.out.println(roomsCount);
        char[][] map = new char[0][0];
        
        return map;
    }

    public String toString(){
        return getClass().getSimpleName() + "[Donjon:" + donjon + "]";
    }
}
