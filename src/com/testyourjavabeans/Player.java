package com.testyourjavabeans;

import java.io.*;

public class Player implements Serializable { //need to implement serializable?

    private String name;
    private Difficulty level;
    private int incorrectAnswerCount = 0;
    private int correctAnswerCount = 0;
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

    public int getIncorrectAnswerCount() {
        return incorrectAnswerCount;
    }

    public void setIncorrectAnswerCount(int incorrectAnswerCount) {
        this.incorrectAnswerCount = incorrectAnswerCount;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

    public boolean shouldPlayerContinue() {
        boolean result = true;
        if (getIncorrectAnswerCount() ==3) {
            result = false;
        }
        return result;
    }


//    public boolean isReturningPlayer() {
//        return returningPlayer;
//    }
//
//    public void setReturningPlayer(boolean returningPlayer) {
//        this.returningPlayer = returningPlayer;
//    }
}