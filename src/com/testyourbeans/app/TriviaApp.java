package com.testyourbeans.app;
import com.apps.util.*;
import com.testyourjavabeans.Board;
import com.testyourjavabeans.Difficulty;
import com.testyourjavabeans.Questions;

import java.util.Scanner;


public class TriviaApp {

    Difficulty difficulty = Difficulty.BEGINNER;
    Prompter prompter = new Prompter(new Scanner(System.in));
    private String name;
    private Board board = Board.getInstance();
    //private Difficulty level; Might not need here

    public void execute() {
        //welcome();
        //showBoard();  //Not sure we need this right here
        //String name = promptForName();
        //updateBoard(name);
        //showBoard();
        askQuestions();
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

    private void askQuestions() {
        Questions question = difficulty.nextQuestion();
        System.out.println(question);
        String answer = promptForAnswer();
        if (question.isCorrectAnswer(answer)) {
            System.out.println("Great");
        }
        else {
            System.out.println("Your answer is wrong!");
        }
    }

    private String promptForAnswer() {
        String answer = prompter.prompt("Please enter your answer:");
        return answer;
    }


}