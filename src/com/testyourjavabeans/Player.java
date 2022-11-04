package com.testyourjavabeans;

class Player {
    //fields
    private String name;
    private Difficulty level = Difficulty.BEGINNER;  //default always starting as beginner

    //constructor
    public  Player(String name, Difficulty level){
        setName(name);
        setLevel(level);
    }

    //accessors
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
