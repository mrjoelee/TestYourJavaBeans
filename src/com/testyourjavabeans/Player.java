package com.testyourjavabeans;

import java.io.*;

public class Player implements Serializable {

    private String name;
    private Difficulty level;
//    private File file = new File("player/playerdata.csv");
//    private boolean returningPlayer;


    public Player(String name, Difficulty level) {
        setName(name);
        setLevel(level);
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

//    public boolean isReturningPlayer() {
//        return returningPlayer;
//    }
//
//    public void setReturningPlayer(boolean returningPlayer) {
//        this.returningPlayer = returningPlayer;
//    }
}