package main;

import main.bestiary.Bestiary;
import main.file.FileLoader;

public class MonsterView {
    public static void load() {
        for(Bestiary m : Bestiary.values()){
            System.out.println("Mob : " + m.getName());
            FileLoader.print(m.getFileName());
            Game.pressToContinue();
        }
    }
}