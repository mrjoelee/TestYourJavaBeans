package com.testyourjavabeans;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Player implements Serializable {

    private String name;
    private Difficulty level;
    private File file = new File("player/playerdata.csv");
    private boolean returningPlayer;


    public Player(String name, Difficulty level) {
        setName(name);
        setLevel(level);
    }

    public void playerExist() {
        Map<String, Difficulty> playerMap = new TreeMap<>();
        List<String> lines;
        setReturningPlayer(false);
        String tempLine = null;
        //create file if doesn't exit
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            lines = Files.readAllLines(Path.of("player/playerdata.csv"));
            if (lines.isEmpty()) {
                System.out.println("Welcome new player!");
                addPlayerToFile(name, level);
                lines.add(name + "," + level);
            } else {
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
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playerLevelUpdate(Difficulty newLevel) {
        List<String> tempList = null;
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

    public void updateFile(String namePlayer) {

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

    public void setReturningPlayer(boolean returningPlayer) {
        this.returningPlayer = returningPlayer;
    }

    public boolean isReturningPlayer() {
        return returningPlayer;
    }
}