package com.testyourjavabeans;

class Player {
    //fields
    private String name;
    private String level;

    //constructor
    public  Player(String name){
        setName(name);
    }

    //accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
