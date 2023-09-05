package main.donjon;

public enum RoomType {
    ADVICE('A'),
    ENEMY('E'),
    BOSS('B');

    private char cara;

    private RoomType(char cara){
        this.cara = cara;
    }

    public char getCara(){
        return cara;
    }

    public String toString(){
        return getClass().getSimpleName() + "[cara:" + cara + "]";
    }
}