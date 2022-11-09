package com.testyourjavabeans;


import java.io.*;
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
    private Difficulty level = Difficulty.BEGINNER;


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

    //Overrides existing player information. e.g, if "joe was a beginner and advances to intermediate" - updates level.
    public void playerLevelUpdate(Difficulty level, Player newPlayer) {
        String data = (newPlayer.getName() + "," + level);

        try{
            List<String> lines = Files.readAllLines(Path.of("player/playerdata.csv"));
            FileWriter fileWriter = new FileWriter(playerDataFilePath.toFile(), false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String line : lines) {
                if (line.equals(newPlayer.getName() + "," + newPlayer.getLevel().toString())) {
                    bufferedWriter.write(data);
                    bufferedWriter.newLine();
                } else {
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

    //methods checking if player exists
    public void playerExist(Player player) {
        String tempLine = null;
        Map<String, Difficulty> playerMap = new TreeMap<>();
        //create file if file doesn't exit
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            List<String> lines = Files.readAllLines(Path.of("player/playerdata.csv"));
            if (lines.isEmpty()) {
                System.out.println("Welcome New Player!");
                addPlayerToFile(player.getName(), player.getLevel());
                lines.add(name + "," + level.toString());
            } else {
                for (String line : lines) {
                    String playerName = line.split(",")[0];
                    Difficulty newLevel = Difficulty.valueOf(line.split(",")[1]);
                    playerMap.put(playerName, newLevel);
                }
                if (playerMap.containsKey(player.getName())) {
                    System.out.println("Welcome: " + player.getName() + " Back to Level: " + playerMap.get(player.getName()));
                    player.setLevel(playerMap.get(player.getName()));
                } else {
                    System.out.println("Welcome New Player");
                    addPlayerToFile(player.getName(), player.getLevel());
                    tempLine = (player.getName() + "," + player.getLevel());
                }

            }
            lines.add(tempLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //adds a player to the file
    public void addPlayerToFile(String namePlayer, Difficulty levelPlayer) {
        try (PrintWriter addPlayer = new PrintWriter(new FileWriter(playerDataFilePath.toFile(), true))){
            String data = (namePlayer + "," + levelPlayer);
            addPlayer.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


