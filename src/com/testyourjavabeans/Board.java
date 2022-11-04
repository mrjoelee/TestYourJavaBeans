package com.testyourjavabeans;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class Board implements Serializable {
    //fields
//    private static final String questionPath = null;
//    private static final String answerPath = null;
    private static final String playerFilePath = "player/board.dat";

    //business methods
    private Board(){

    }

    public static Board getInstance() {
        Board board = null;

        if (Files.exists(Path.of(playerFilePath))) {

        }

        return board;
    }

    private void save(){

    }

    private void update(){

    }

    public void show(){
        //level, questions
    }
    /*
     * Collections of Questions and Answer Map: K: (Integer)Question# V: (String)Questions itself
     * Map K: (Integer)Answer# V: (String) Answers
     */

}
