package main.donjon;

import main.Color;
import main.entity.Renaud;
import main.view.GameView;

public class DonjonGenerator {
    public final static int FLOOR_COUNT = Theme.getSize();
    public final static int ROOM_WIDTH = 25;
    public final static int ROOM_HEIGHT = 9;
    public final static int ROOM_WAY = 5;
    public final static char CHAR_UP_LEFT = GameView.CHAR_UP_LEFT;
    public final static char CHAR_UP_RIGHT = GameView.CHAR_UP_RIGHT;
    public final static char CHAR_DOWN_LEFT = GameView.CHAR_DOWN_LEFT;
    public final static char CHAR_DOWN_RIGHT = GameView.CHAR_DOWN_RIGHT;
    public final static char CHAR_VERTICAL = GameView.CHAR_VERTICAL;
    public final static char CHAR_HORIZONTAL = GameView.CHAR_HORIZONTAL;
    public final static char CHAR_WAY = GameView.CHAR_WAY;

    private Donjon donjon;
    private Renaud player;
    private String[][] map;

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

    public DonjonFloor getCurrentFloor(){
        return donjon.getFloor(player.getStage());
    }

    public DonjonRoom getCurrentRoom(){
        return donjon.getRoom(player.getStage(), player.getRoom());
    }

    public Theme getCurrentTheme(){
        return donjon.getTheme(player.getStage());
    }

    public boolean exists(int stage, int room){
        DonjonFloor dj = donjon.getFloor(stage);
        if(dj == null) return false;
        DonjonRoom dr = dj.getRoom(room);
        if(dr == null) return false;
        return true;
    }

    private void loadCurrentStage(){
        generateStage();
        loadRooms();
    }

    private void generateStage(){
        int roomsCount = donjon.getRoomsCount(player.getStage());

        int width = roomsCount * (ROOM_WIDTH + ROOM_WAY) - ROOM_WAY;
        int height = ROOM_HEIGHT;

        map = new String[height][width];

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                int valueX = x % (ROOM_WIDTH + ROOM_WAY);
                RoomType currentType = null;
                char c = ' ';
                
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
                    currentType = getRoomTypeByWidthValue(width, x);
                }else if(valueX >= ROOM_WIDTH && y == ROOM_HEIGHT / 2){
                    c = CHAR_WAY;
                }

                String s = String.valueOf(c);
                if(currentType != null){
                    s = Color.charToColor(currentType.getColor(), c);
                }
                map[y][x] = s;
            }
        }
    }

    private RoomType getRoomTypeByWidthValue(int width, int x){
        int roomsCount = (width + ROOM_WAY) / (ROOM_WIDTH + ROOM_WAY);
        int room = (x * roomsCount) / width;
        
        DonjonFloor dj = getCurrentFloor();
        if(dj == null) return null;
        DonjonRoom dr = dj.getRoom(room);
        if(dr == null) return null;
        return dr.getType();
    }

    private void loadRooms(){
        DonjonFloor floor = getCurrentFloor();
        if(floor == null) return;
        DonjonRoom[] rooms = floor.getRooms();
        for(int i = 0; i < rooms.length; i++){
            loadRoom(rooms[i], i);
        }
    }

    private void loadRoom(DonjonRoom dr, int room){
        RoomType type = dr.getType();
        int x = ROOM_WIDTH / 2 + (ROOM_WIDTH + ROOM_WAY) * room;
        int y = ROOM_HEIGHT / 2;
        
        map[y][x] = Color.charToColor(type.getColor(), type.getCara());
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
                String c = map[y][x];
                sb.append(c + "");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private void drawPlayer(){
        StringBuilder sb = new StringBuilder();
        int room = player.getRoom();
        int width = ROOM_WIDTH / 2 + ROOM_WIDTH * room + ROOM_WAY * room;
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < width; x++){
                sb.append(" ");
                String playerMiniMap = Renaud.PLAYER_MINI_MAP;
                if(x == width - playerMiniMap.length()/2 - 1 && y == 3){
                    sb.append(playerMiniMap);
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
