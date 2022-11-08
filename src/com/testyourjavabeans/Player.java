package com.testyourjavabeans;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Player implements Serializable {

    private String name;
    private Difficulty level;
    private File file = new File("player/playerdata.csv");
    private int incorrectAnswerCount = 0;
    private int correctAnswerCount = 0;
    //private boolean returningPlayer; //I think this can be deleted along with and associated uses
    Map<String, Difficulty> playerMap = new TreeMap<>();

    public Player(String name, Difficulty level) {
        setName(name);
        setLevel(level);
    }
    //methods checking if player exists, saves a new player to the file "playerdata.csv"
    public void playerExist() {

        List<String> lines; // what's the purpoe of this list.
        //setReturningPlayer(false); can delete?
        String tempLine = null;
        //create file if doesn't exit
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            lines = Files.readAllLines(Path.of("player/playerdata.csv"));
            // This eliminates the scenario where the csv file only has one name and the same player returns and gets
            // counted again and added to the csv file
            if (lines.isEmpty()) {
                System.out.println("Welcome new player!");
                addPlayerToFile(name, level);
                lines.add(name + "," + level);
            }
            else {
                for (String line : lines) {
                    String playerName = line.split(",")[0];
                    Difficulty playerLevel = level.valueOf(line.split(",")[1]);
                    playerMap.put(playerName, playerLevel);
                }
                if (playerMap.containsKey(name)) {
                    System.out.println("Welcome back player: " + name + " your level is: " + playerMap.get(name));
                    setLevel(playerMap.get(name));
                } else {
                    System.out.println("Welcome new player");
                    addPlayerToFile(name, level);
                    tempLine = (name + "," + level);
                }

            }
            lines.add(tempLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // called by playerExist() to add new player to the player csv file
    public void addPlayerToFile(String namePlayer, Difficulty levelPlayer) {
        try {
            String data = (namePlayer + "," + levelPlayer);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data); // can combine, possible use use new printwriter(filewriter())
            bufferedWriter.newLine();
            bufferedWriter.close(); // we can use try with resources.
            fileWriter.close();     // we can use try with resources.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method creates a new playerdata.csv file with the updated players level of difficulty
    public void playerLevelUpdate(Difficulty newLevel) {
        //List<String> tempList = null;  can delete
        String namePlayer = getName();
        String data = (namePlayer + "," + newLevel);

        try {
            List<String> lines = Files.readAllLines(Path.of("player/playerdata.csv"));
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line : lines) {
                if (line.equals(getName() + "," + getLevel().toString())) {
                    bufferedWriter.write(data);
                    bufferedWriter.newLine();
                }
                else {
                    String tempLine = line;
                    bufferedWriter.write(tempLine);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void updateFile(String namePlayer) {  can delete?
//
//    }

    public boolean shouldPlayerContinue() {
        boolean result = true;
        if (getIncorrectAnswerCount() ==3) {
            result = false;
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

    //    public void setReturningPlayer(boolean returningPlayer) {  can delete?
//        this.returningPlayer = returningPlayer;
//    }
//
//    public boolean isReturningPlayer() {
//        return returningPlayer;
//    }


}