package com.testyourbeans.app;

import com.apps.util.*;
import com.testyourjavabeans.Board;
import com.testyourjavabeans.Difficulty;
import com.testyourjavabeans.Player;
import com.testyourjavabeans.Questions;

import java.util.Scanner;


public class TriviaApp {

    Difficulty difficulty = Difficulty.BEGINNER;
    Prompter prompter = new Prompter(new Scanner(System.in));
    //private String name;
    private String continueGame = "y";
    Player player;
    private final Board board = Board.getInstance();
    //private Difficulty level; Might not need here

    public void execute() {
        //welcome();
        //showBoard();  //Not sure we need this right here
        //String name = promptForName();
        String name = promptName();
        updateBoard(name, difficulty);
        startRoundOfQuestions();
    }

    private void updateBoard(String name, Difficulty difficulty) {
        board.update(name, difficulty);
    }

    public void startRoundOfQuestions() {

        //starts the initial round of questions
        difficulty = getDifficulty();
        difficulty.createQuestionBank();
        while (getContinueGame().equals("y") && difficulty.getListSize() > 0) {
            askQuestions();
        }

        //Player moves on to intermediate questions if questions for round 1 used up successfully
        if (difficulty.getListSize() == 0) {
            player.setLevel(Difficulty.INTERMEDIATE);
            System.out.println("Congratulations " + player.getName() + " you have graduated to level: " + player.getLevel() + "\n");
            setDifficulty();
            difficulty = getDifficulty();
            difficulty.createQuestionBank();
            while (getContinueGame().equals("y") && difficulty.getListSize() > 0) {
                askQuestions();
            }

        }
        Console.clear();
        //Player moves on to advanced questions if questions for round 2 used up successfully
        if (difficulty.getListSize() == 0) {
            player.setLevel(Difficulty.ADVANCED);
            System.out.println("Congratulations " + player.getName() + " you have graduated to level: " + player.getLevel() + "\n");
            setDifficulty();
            difficulty = getDifficulty();
            difficulty.createQuestionBank();
            while (getContinueGame().equals("y") && difficulty.getListSize() > 0) {
                askQuestions();
            }
            System.out.println("Congratulations " + player.getName() + " you have won the game.");
        }
    }

//    public void welcome() {
//        System.out.println("Welcome to the Java Trivia App");
//    }
//
//    //new player name and level will save into a csv file.
//    //public String promptForName() {
//        //String name = prompter.prompt("Please enter your name:");
//
//        /* we will come back to it.
//        try(PrintWriter writer = new PrintWriter(new FileWriter("player/playerdata.csv"))){
//            {
//                writer.println(name);
//            }
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//         */
//        return name;
//    }


    //    public void showBoard() {
//        board.show();
//
//    }
    private String promptName() {
        String name = null;
        name = prompter.prompt("Please enter your name:");  // change "answer" to name
        System.out.println();
        player = new Player(name, Difficulty.BEGINNER);
        // if a statement = if player exist (method), pass in the name, and calls the method under Player class,
        // player method will check the csv file, if it 's true it will return that player.
        //if not it will setPlayer as new player.


        //it creates the player
        setPlayer(player);
        return name;
    }

    // method() used by startRoundOfQuestions - this shows the current question, prompts for a response
    // then determines if correct and sets field which determines whether or not to continue (continuesGame)
    private void askQuestions() {
        Questions question = getDifficulty().nextQuestion();
        System.out.println(question);
        String answer = promptForAnswer();
        if (question.isCorrectAnswer(answer)) {
            System.out.println("\nGreat\n");
        } else {
            System.out.println("\nYour answer is wrong!\n");
            setContinueGame("n");
        }
    }

    private void showBoard() {
        Console.clear();
        //board.show();
    }

    // review this code for usage.
    private String promptForAnswer() {
        String answer = prompter.prompt("Please enter your answer:");
        return answer;
    }

    public String getContinueGame() {
        return continueGame;
    }

    public void setContinueGame(String continueGame) {
        this.continueGame = continueGame;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty() {
        this.difficulty = player.getLevel();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}