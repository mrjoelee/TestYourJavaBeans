package com.testyourjavabeans;

public class Player {

    private String name;
    private Difficulty level = Difficulty.BEGINNER;

    public  Player(String name, Difficulty level){
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

//    public Player playerExists(String name, Difficulty level) {
//        if () {
//
//        }
//    }
}
