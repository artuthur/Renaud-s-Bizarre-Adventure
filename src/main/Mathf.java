package main;

import java.util.ArrayList;
import java.util.List;

public class Mathf {
    public static double random(){
        return Math.random();
    }

    public static double random(double max){
        return random(0, max);
    }

    public static double random(double min, double max){
        return min + (max - min + 1) * random();
    }

    public static int random(int max){
        return random(0, max);
    }

    public static int random(int min, int max){
        return min + (int)((max - min + 1) * random());
    }

    public static <T> T random(T[] array){
        if(array == null) return null;
        List<T> list = new ArrayList<>();
        for(T t : array) list.add(t);
        return random(list);
    }
    
    public static <T> T random(List<T> list){
        if(list == null) return null;
        if(list.size() == 0) return null;
        return list.get(random(list.size() - 1));
    } 
}
