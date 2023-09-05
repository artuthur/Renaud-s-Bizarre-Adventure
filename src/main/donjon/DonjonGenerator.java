package main.donjon;

import main.entity.Renaud;

public class DonjonGenerator {
    public final static int ROOM_WIDTH = 15;
    public final static int ROOM_HEIGHT = 7;
    public final static int ROOM_BETWEEN_WAY = 5;
    public final static char CHAR_UP_LEFT = '╯';
    public final static char CHAR_UP_RIGHT = '╰';
    public final static char CHAR_DOWN_LEFT = '╮';
    public final static char CHAR_DOWN_RIGHT = '╭';
    public final static char CHAR_VERTICAL = '│';
    public final static char CHAR_HORIZONTAL = '─';
    public final static char CHAR_WAY = '═';

    private Donjon donjon;
    private Renaud player;
    private char[][] map;

    public DonjonGenerator(Donjon donjon, Renaud player){
        this.donjon = donjon;
        this.player = player;
        loadCurrentStage();
    }

    public Donjon getDonjon(){
        return donjon;
    }

    public Renaud getPlayer(){
        return player;
    }

    public void loadCurrentStage(){
        if(donjon == null) System.err.println("Le donjon est null");
        if(player == null) System.err.println("Le joueur est null");

        generateStage(player.getStage());
        loadRooms(player.getStage());
    }

    public void generateStage(int stage){
        int roomsCount = donjon.getNumberOfRooms(stage);

        int width = roomsCount * ROOM_WIDTH + (roomsCount - 1) * ROOM_BETWEEN_WAY;
        int height = ROOM_HEIGHT;

        map = new char[height][width];

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                char c = ' ';

                int valueX = x % (ROOM_WIDTH + ROOM_BETWEEN_WAY);

                if(valueX >= 0 && valueX <= ROOM_WIDTH - 1){
                    if(valueX % (ROOM_WIDTH - 1) == 0) c = CHAR_VERTICAL;
                    if(y % (ROOM_HEIGHT - 1) == 0) c = CHAR_HORIZONTAL;
                    if(y == 0){
                        if(valueX == 0) c = CHAR_DOWN_RIGHT;
                        if(valueX == ROOM_WIDTH - 1) c = CHAR_DOWN_LEFT;
                    }else if(y == ROOM_HEIGHT - 1){
                        if(valueX == 0) c = CHAR_UP_RIGHT;
                        if(valueX == ROOM_WIDTH - 1) c = CHAR_UP_LEFT;
                    }
                }else if(valueX >= ROOM_WIDTH && y == ROOM_HEIGHT / 2){
                    c = CHAR_WAY;
                }

                map[y][x] = c;
            }
        }
    }

    public void loadRooms(int stage){
        DonjonFloor floor = donjon.getFloor(stage);
        if(floor == null) return;
        DonjonRoom[] rooms = floor.getRooms();
        for(int i = 0; i < rooms.length; i++){
            loadRoom(rooms[i], i);
        }
    }

    public void loadRoom(DonjonRoom dr, int room){
        RoomType type = dr.getType();
        int x = ROOM_WIDTH / 2 + ROOM_WIDTH * room + ROOM_BETWEEN_WAY * room;
        int y = ROOM_HEIGHT / 2;
        char c = 'X';

        switch(type){
            case ADVICE : c = RoomType.ADVICE.getCara(); break;
            case ENEMY : c = RoomType.ENEMY.getCara(); break;
            case BOSS : c = RoomType.BOSS.getCara(); break;
        }
        
        map[y][x] = c;
    }

    public void drawDonjon(){
        //BestiaryLoader.clearScreen();
        draw();
    }

    public void draw(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                char c = map[y][x];
                sb.append(c + "");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public String toString(){
        return getClass().getSimpleName() + "[donjon:" + donjon + ", player:" + player + "]";
    }
}
