package com.testyourbeans.app;
import com.apps.util.*;
import com.testyourjavabeans.Board;
import com.testyourjavabeans.Levels;

import java.util.Scanner;


public class TriviaApp {

    Prompter prompter = new Prompter(new Scanner(System.in));
    private String name;
    private Board board = Board.getInstance();
    //private Levels level; Might not need here

    public void execute() {
        welcome();
        //showBoard();  //Not sure we need this right here
        String name = promptForName();
        //updateBoard(name);
        //showBoard();
    }

    public void welcome() {
        System.out.println("Welcome to the Java Trivia App");
    }

    public String promptForName() {
        String name = prompter.prompt("Please enter your name");
        return name;
    }

    public void showBoard() {
        board.show();

    }





}