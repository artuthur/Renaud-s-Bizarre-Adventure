package main.donjon;

public class Room<T> {
    private T insideTheRoom;

    public Room(T insideTheRoom){
        this.insideTheRoom = insideTheRoom;
    }

    public T getInside(){
        return insideTheRoom;
    }

    public String toString(){
        return getClass().getSimpleName() + "[insideTheRoom:" + insideTheRoom + "]";
    }
}
