package main.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {
    public final static String SEPARATOR = File.separator;
    public final static String PATH = "res" + SEPARATOR;

    public static String load(String fileName){
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
        return sb.toString();
    }
}