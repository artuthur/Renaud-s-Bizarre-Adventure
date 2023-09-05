package main;

public class Mathf {
    public static double random(){
        return Math.random();
    }
    public static double random(double min, double max){
        return min + (max - min + 1) + Math.random();
    }
    public static int random(int min, int max){
        return (int)random(min, max);
    }
}
