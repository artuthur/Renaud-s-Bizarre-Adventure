package main.donjon;

import main.entity.Renaud;

public class DonjonGenerator {
    public final static int FLOOR_COUNT = Theme.getSize();
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

    public DonjonRoom getCurrentRoom(){
        return donjon.getRoom(player.getStage(), player.getRoom());
    }

    private void loadCurrentStage(){
        int currentStage = player.getStage();
        generateStage(currentStage);
        loadRooms(currentStage);
    }

    private void generateStage(int stage){
        int roomsCount = donjon.getRoomsCount(stage);

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

    private void loadRooms(int stage){
        DonjonFloor floor = donjon.getFloor(stage);
        if(floor == null) return;
        DonjonRoom[] rooms = floor.getRooms();
        for(int i = 0; i < rooms.length; i++){
            loadRoom(rooms[i], i);
        }
    }

    private void loadRoom(DonjonRoom dr, int room){
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
        if(donjon == null) System.err.println("Le donjon est null");
        else if(player == null) System.err.println("Le joueur est null");
        else{
            loadCurrentStage();
            drawRooms();
            drawPlayer();
        }
    }

    private void drawRooms(){
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

    private void drawPlayer(){
        StringBuilder sb = new StringBuilder();
        int room = player.getRoom();
        int width = ROOM_WIDTH / 2 + ROOM_WIDTH * room + ROOM_BETWEEN_WAY * room;
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < width; x++){
                sb.append(" ");
                String name = player.getName();
                if(x == width - name.length()/2 && y == 3){
                    sb.append(name);
                    continue;
                }
            }
            if(y == 0) sb.append('ᐱ');
            if(y == 1) sb.append('|');
            if(y == 2) sb.append('o');
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void print(){
        if(donjon == null) return;
        donjon.print();
    }

    public String toString(){
        return getClass().getSimpleName() + "[donjon:" + donjon + ", player:" + player + "]";
    }
}
