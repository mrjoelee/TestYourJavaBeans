package com.testyourjavabeans;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Player implements Serializable {

    private String name;
    private Difficulty level;

    public  Player(String name, Difficulty level){
        setName(name);
        setLevel(level);
    }

    public boolean playerExist(String name){
        boolean result = false;
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of("player/playerdata.csv"));
            for(String line : lines){
                String playerName = line.split(",")[0];
                if(name.equals(playerName)){
                    result = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getLevel() {
        return level;
    }

    public void setLevel(Difficulty level) {
        this.level = level;
    }

}