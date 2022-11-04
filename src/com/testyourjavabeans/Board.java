package com.testyourjavabeans;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Serializable {
    //fields
    private static final String playerFilePath = "player/board.dat";
    private static final String playerDataFilePath = "player/playerData.csv";

    private final Map<String, Difficulty> playerMap = loadPlayerMap();

    //business methods
    private Board(){

    }

    public static Board getInstance() {
        Board board = null;

        if (Files.exists(Path.of(playerFilePath))) {
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(playerFilePath))) {
                board = (Board) in.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            board = new Board();
        }
        return board;
    }

    private void save(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playerFilePath))) {
            out.writeObject(this);
        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    private void update(){
        Player player = null;

//        if () {
//
//        }
//        else {
//
//        }
    }

    public void show(){
        //level, questions
    }
    /*
     * Collections of Questions and Answer Map: K: (Integer)Question# V: (String)Questions itself
     * Map K: (Integer)Answer# V: (String) Answers
     */


    //getting the players name and levels to be added to the map.
    private Map<String, Difficulty> loadPlayerMap() {
        Map<String, Difficulty> playerMap = new HashMap<>();

        try {
            List<String> playerInfo = Files.readAllLines(Path.of(playerDataFilePath)); // only names, there is no level

            for (String info : playerInfo) {
                String[] splits = info.split(",");
                String name = splits[0];
                Difficulty level = Difficulty.valueOf(splits[1]);
                playerMap.put(name,level);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return playerMap;
    }


}
