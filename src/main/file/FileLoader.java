package main.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
    public final static String SEPARATOR = File.separator;
    public final static String PATH = "res" + SEPARATOR;

    public static String load(String fileName){
        return load(PATH, fileName);
    }

    public static String load(String path, String fileName){
        File file = FileFinder.find(fileName);
        if(file == null) return null;
        
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while(br.ready()){
                sb.append(br.readLine() + "\n");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static void print(String fileName){
        print(PATH, fileName);
    }

    public static void print(String path, String fileName){
        System.out.println(load(path, fileName));
    }
}