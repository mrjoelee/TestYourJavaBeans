package com.testyourjavabeans;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Board implements Serializable {
    //fields
    //private static final String playerFilePath = "player/board.csv";
    private static final String playerDataFilePath = "player/playerdata.csv";

//    private final Map<String, Difficulty> playerMap = loadPlayerMap();

    /*
     * no outside instantiation - getInstance() is the sole access point
     */
    private Board(){

    }
    //since it's static is running above of Board methods.
    /*
     * If player/board.dat exists, read Board object from that binary file.
     * If not, create new.
     * Finally, return it.
     */
    public static Board getInstance() {
        Board board = null;

        if (Files.exists(Path.of(playerDataFilePath))) {
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(playerDataFilePath))) {
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
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playerDataFilePath))) {
            out.writeObject(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //so a list works, but a map won't work.
    // a map K = name, V = difficulty.

    private final List<Player> playerMap1 = new ArrayList<>();

    public void update(String name, Difficulty difficulty){
        Player player = null;

        // currently saves Joe/Beginner
        player = new Player(name, difficulty);
        playerMap1.add(player);
        save();
    }


//    private final List<Player> playerMap1 = new ArrayList<>();
//
//    public void update(String name, Difficulty difficulty){
//        Player player = null;
//                player = new Player(name, difficulty);
//                playerMap1.add(player);
//        save();
//    }
//
//    public void show(){
//        if(playerMap1.isEmpty()){
//            System.out.println("The board is currently empty\n");
//        } else {
//            System.out.println("TestJavaYourBeans Results");
//            System.out.println("Name     Difficulty");
//            playerMap1.forEach((k,v) -> System.out.printf("%s      %s\n", k, v));
//        }
//    }
    /*
     * Collections of Questions and Answer Map: K: (Integer)Question# V: (String)Questions itself
     * Map K: (Integer)Answer# V: (String) Answers
     */


    //getting the players name and levels to be added to the map.
//    private Map<String, Difficulty> loadPlayerMap() {
//        Map<String, Difficulty> playerMap = new HashMap<>();
//
//        try {
//            List<String> playerInfo = Files.readAllLines(Path.of(playerDataFilePath)); // only names, there is no level
//
//            for (String info : playerInfo) {
//                String[] splits = info.split(",");
//                String name = splits[0];
//                Difficulty level = Difficulty.BEGINNER;
//                playerMap.put(name,level);
//            }
//
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        return playerMap;
//    }

}
