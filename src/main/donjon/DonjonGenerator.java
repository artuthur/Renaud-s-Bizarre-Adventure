package main.donjon;

import main.entity.Renaud;

public class DonjonGenerator {
    public final static int ROOM_SIZE = 5;
    public final static int ROOM_BETWEEN_WAY = 3;
    public final static char CHAR_UP_LEFT = '╬';
    public final static char CHAR_UP_RIGHT = '╬';
    public final static char CHAR_DOWN_LEFT = '╬';
    public final static char CHAR_DOWN_RIGHT = '╬';
    public final static char CHAR_VERTICAL = '║';
    public final static char CHAR_HORIZONTAL = '═';
    public final static char CHAR_WAY = '═';

    private Donjon donjon;
    private Renaud player;

    public DonjonGenerator(Donjon donjon, Renaud player){
        this.donjon = donjon;
        this.player = player;
    }

    public Donjon getDonjon(){
        return donjon;
    }

    public Renaud getPlayer(){
        return player;
    }

    public char[][] loadCurrentRoom(){
        return loadRoom(player.getRoom());
    }

    public char[][] loadRoom(int room){
        player.setStage(4);

        int roomsCount = donjon.getNumberOfRooms(player.getStage());

        int width = roomsCount * ROOM_SIZE + (roomsCount - 1) * ROOM_BETWEEN_WAY;
        int height = ROOM_SIZE;

        char[][] map = new char[height][width];

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                char c = ' ';

                int valueX = x % (ROOM_SIZE + ROOM_BETWEEN_WAY);

                if(valueX >= 0 && valueX <= ROOM_SIZE - 1){
                    // if(y == 0){
                    //     if(valueX == 0) c = CHAR_DOWN_LEFT;
                    //     else if(valueX == ROOM_SIZE - 1) c = CHAR_DOWN_RIGHT;
                    // }
                    // if(y == ROOM_SIZE - 1){
                    //     if(valueX == 0) c = CHAR_DOWN_LEFT;
                    //     else if(valueX == ROOM_SIZE - 1) c = CHAR_DOWN_RIGHT;
                    // }

                    if(valueX % (ROOM_SIZE - 1) == 0 && y % (ROOM_SIZE - 1) == 0){
                        c = CHAR_DOWN_RIGHT;
                    }else if(valueX % (ROOM_SIZE - 1) == 0){
                        c = CHAR_VERTICAL;
                    }else if(y % (ROOM_SIZE - 1) == 0){
                        c = CHAR_HORIZONTAL;
                    }
                }else if(valueX >= ROOM_SIZE && y == ROOM_SIZE / 2){
                    c = CHAR_WAY;
                }

                map[y][x] = c;
            }
        }

        // +---+   +---+   +---+   +---+   +---+
        // |   |   |   |   |   |   |   |   |   |
        // |   |---|   |---|   |---|   |---|   |
        // |   |   |   |   |   |   |   |   |   |
        // +---+   +---+   +---+   +---+   +---+
        
        return map;
    }

    public void drawDonjon(){
        System.out.println(toString(loadCurrentRoom()));
    }

    public static String toString(char[][] map){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                char c = map[y][x];
                sb.append(c + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toString(){
        return getClass().getSimpleName() + "[donjon:" + donjon + ", player:" + player + "]";
    }
}
