package com.testyourbeans.app;
import com.apps.util.*;
import com.testyourjavabeans.Board;

import java.util.Scanner;


public class TriviaApp {

    Prompter prompter = new Prompter(new Scanner(System.in));
    private String name;
    private Board board = Board.getInstance();
    //private Difficulty level; Might not need here

    public void execute() {
        //welcome();
        //showBoard();  //Not sure we need this right here
        String name = promptForName();
        //updateBoard(name);
        //showBoard();
    }

    public void welcome() {
        System.out.println("Welcome to the Java Trivia App");
    }

    //new player name and level will save into a csv file.
    public String promptForName() {
        String name = prompter.prompt("Please enter your name:");

        /* we will come back to it.
        try(PrintWriter writer = new PrintWriter(new FileWriter("player/playerData.csv"))){
            {
                writer.println(name);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
         */
        return name;
    }

    public void showBoard() {
        board.show();

    }





}