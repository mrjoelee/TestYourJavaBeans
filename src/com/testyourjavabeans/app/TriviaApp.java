package com.testyourjavabeans.app;

import com.apps.util.*;
import com.testyourjavabeans.*;

import java.util.Scanner;

public class TriviaApp {

    //Difficulty difficulty = Difficulty.BEGINNER;
    Prompter prompter = new Prompter(new Scanner(System.in));
    //private String name;
    private boolean continueGame = true;
    Intro intro = new Intro();
    Player player;
    //private final Board board = Board.getInstance();
    //private Difficulty level; Might not need here

    public void execute() {
        introSequenceShow();
        //welcome();
        //showBoard();  //Not sure we need this right here
        //String name = promptForName();
        String name = promptName();
        //updateBoard(name, difficulty);
        startRoundOfQuestions();
    }

    //shows the intro of our team name and game name.
    private void introSequenceShow() {
        intro.show();
        intro.next();
    }

//    private void updateBoard(String name, Difficulty difficulty) {
//        board.update(name, difficulty);
//    }

    //we will have to refactor this session.
    public void startRoundOfQuestions() {
        switch (player.getLevel()) {
            case BEGINNER:
                //difficulty = getDifficulty();
                //difficulty.createQuestionBank();
                //(player.getLevel()).createQuestionBank();
                while (getContinueGame() && (player.getCorrectAnswerCount() < 5)) {
                    answer();
                }

            case INTERMEDIATE:
                if (getContinueGame()) {
                    player.setCorrectAnswerCount(0);
                    player.playerLevelUpdate(Difficulty.INTERMEDIATE); // we have 2 player levels difficulty
                    player.setLevel(Difficulty.INTERMEDIATE);
                    System.out.println(player.getName() + " you are now at level: " + player.getLevel() + "\n");
                    //setDifficulty();  // can delete?
                    //difficulty = getDifficulty();
                    //(player.getLevel()).createQuestionBank();
                    while (getContinueGame() && player.getCorrectAnswerCount() < 5) {
                        answer();
                    }

                }

            case ADVANCED:
                if (getContinueGame()) {
                    player.setCorrectAnswerCount(0);
                    player.playerLevelUpdate(Difficulty.ADVANCED);
                    player.setLevel(Difficulty.ADVANCED);
                    System.out.println(player.getName() + " you are now at level: " + player.getLevel() + "\n");
                    //setDifficulty(); // can delete?
                    //difficulty = getDifficulty();
                    //(player.getLevel()).createQuestionBank();
                    while (getContinueGame() && player.getCorrectAnswerCount() < 5) {
                        answer();
                    }
                    if (player.getCorrectAnswerCount() == 5) {
                        System.out.println("Congratulations " + player.getName() + " you have won the game.");
                    }
                }
        }
    }


    //starts the initial round of questions
//        difficulty = getDifficulty();
//        difficulty.createQuestionBank();
//        while (getContinueGame().equals("y") && difficulty.getListSize() > 0) {
//            askQuestions();
//        }
//
//        //Player moves on to intermediate questions if questions for round 1 used up successfully
//        if (difficulty.getListSize() == 0) {
//            player.playerLevelUpdate(Difficulty.INTERMEDIATE);
//            player.setLevel(Difficulty.INTERMEDIATE);
//            System.out.println("Congratulations " + player.getName() + " you have graduated to level: " + player.getLevel() + "\n");
//            setDifficulty();
//            difficulty = getDifficulty();
//            difficulty.createQuestionBank();
//            while (getContinueGame().equals("y") && difficulty.getListSize() > 0) {
//                askQuestions();
//            }
//
//        }
//        Console.clear();
//        //Player moves on to advanced questions if questions for round 2 used up successfully
//        if (difficulty.getListSize() == 0) {
//            player.playerLevelUpdate(Difficulty.ADVANCED);
//            player.setLevel(Difficulty.ADVANCED);
//            System.out.println("Congratulations " + player.getName() + " you have graduated to level: " + player.getLevel() + "\n");
//            setDifficulty();
//            difficulty = getDifficulty();
//            difficulty.createQuestionBank();
//            while (getContinueGame().equals("y") && difficulty.getListSize() > 0) {
//                askQuestions();
//            }
//            System.out.println("Congratulations " + player.getName() + " you have won the game.");
//        }
//    }

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

    //prompting for name for new player
    private String promptName() {
        intro.showBanner();
        String namePattern = "([a-zA-Z ]{2,20})";
        String name = prompter.prompt("Please enter your first and last name:", namePattern,
                "Invalid Data: Must be between 2-20 Characters (insensitive)\n");
        System.out.println();
        player = new Player(name, Difficulty.BEGINNER);

        //verifying if the player has already played, if so will retrieve the player.
        player.playerExist();

        //it creates the player
       // setPlayer(player);
        return name;
    }

    // method() used by startRoundOfQuestions - this shows the current question, prompts for a response
    // then determines if correct and sets field which determines whether or not to continue (continuesGame)
    // also gets and sets number of correct and incorrect answers from player class
    private void answer() {
        int answerCorrectCount = player.getCorrectAnswerCount();
        int answerIncorrectCount = player.getIncorrectAnswerCount();
        Questions question = (player.getLevel()).nextQuestion();
        System.out.println(question);
        String answer = promptForAnswer();
        if (question.isCorrectAnswer(answer)) {
            player.setCorrectAnswerCount(answerCorrectCount += 1); //not sure why saying this, it does get used
            System.out.println("\nGreat\n");
        } else {
            System.out.println("\nYour answer is wrong!\n");
            player.setIncorrectAnswerCount(answerIncorrectCount += 1); //this does get used
        }

        if (player.shouldPlayerContinue()) {
            setContinueGame(true);
        }
        else {
            setContinueGame(false);
        }

        Console.pause(1000);
        Console.clear();
        intro.showBanner();
    }

//    private void showBoard() {
//        Console.clear();
//        //board.show();
//    }

    // review this code for usage.
    private String promptForAnswer() {
        String answer = prompter.prompt("Please enter your answer:");
        return answer;
    }

    //these setters - we will need to work together to move into the according classes.
    public boolean getContinueGame() {
        return continueGame;
    }

    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }

//    public Difficulty getDifficulty() {
//        return difficulty;
//    }
//
//    public void setDifficulty() {
//        this.difficulty = player.getLevel();
//    }
//
//    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
}