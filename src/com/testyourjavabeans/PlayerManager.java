package com.testyourjavabeans;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class PlayerManager {
    private final Path playerDataFilePath;
    private File file = new File("player/playerdata.csv");
    private String name;
    private Difficulty level;
    private boolean returningPlayer;


    Map<String, Difficulty> playerMap = new TreeMap<>();
    Player player;

    public PlayerManager(String playerDataFilePath) {
        this.playerDataFilePath = Path.of(playerDataFilePath);
    }

    public List<Player> upload() throws IOException {
        List<Player> completeList = new ArrayList<>();

        Files.lines(playerDataFilePath).forEach(line -> {
            String[] tokens = line.split(",");

            String name = tokens[0];
            Difficulty level = Difficulty.valueOf(tokens[1]);

            completeList.add(new Player(name, level));
        });
        return completeList;
    }


    public void setReturningPlayer(boolean returningPlayer) {
        this.returningPlayer = returningPlayer;
    }

    public boolean isReturningPlayer() {
        return returningPlayer;
    }

    public void playerLevelUpdate(Difficulty level) {
        List<String> tempList = null;
        String namePlayer = name;
        String data = (name + "," + level);

        try {
            List<String> lines = Files.readAllLines(playerDataFilePath);
            FileWriter fileWriter = new FileWriter(String.valueOf(playerDataFilePath), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line : lines) {
                if (line.equals(name + "," + level)) {
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

    //region methods checking if player exists, saves a new player to the file "playerdata.csv"
    public void playerExist(Player player) {
//        setReturningPlayer(false);
        String tempLine = null;
        //create file if doesn't exit
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            List<String> lines = Files.readAllLines(Path.of("player/playerdata.csv"));
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
                    player.setLevel(playerMap.get(level));
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


    public void addPlayerToFile(String namePlayer, Difficulty levelPlayer) {
        try {
            String data = (namePlayer + "," + levelPlayer);
            FileWriter fileWriter = new FileWriter(playerDataFilePath.toFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close(); // we can use try with resources.
            fileWriter.close();     // we can use try with resources.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

