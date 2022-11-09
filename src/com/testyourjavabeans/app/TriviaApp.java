package com.testyourjavabeans.app;

import com.apps.util.*;
import com.testyourjavabeans.*;

import java.util.Locale;
import java.util.Scanner;

public class TriviaApp {

    private Prompter prompter = new Prompter(new Scanner(System.in));
    private boolean continueGame = true;
    private Intro intro = new Intro();
    private Player player;
    private PlayerManager manager = new PlayerManager("player/playerdata.csv");

    public void execute() {
        introSequenceShow();
        promptName();
        startRoundOfQuestions();
    }

    //shows the intro of our team name and name of game.
    private void introSequenceShow() {
        intro.show();
        intro.next();
    }

    //we will have to refactor this session.
    private void startRoundOfQuestions() {
        switch (player.getLevel()) {
            case BEGINNER:
                System.out.println("You are at beginnner level.");
                while (getContinueGame() && (player.getCorrectAnswerCount() < 5)) {
                    answer();
                }

            case INTERMEDIATE:
                if (getContinueGame()) {
                    player.setCorrectAnswerCount(0);
                    manager.playerLevelUpdate(Difficulty.INTERMEDIATE, player);
                    player.setLevel(Difficulty.INTERMEDIATE);
                    System.out.println(player.getName() + " you are now at level: " + player.getLevel() + "\n");
                    while (getContinueGame() && player.getCorrectAnswerCount() < 5) {
                        answer();
                    }
                }

            case ADVANCED:
                if (getContinueGame()) {
                    player.setCorrectAnswerCount(0);
                    manager.playerLevelUpdate(Difficulty.ADVANCED, player);
                    player.setLevel(Difficulty.ADVANCED);
                    System.out.println(player.getName() + " you are now at level: " + player.getLevel() + "\n");
                    while (getContinueGame() && player.getCorrectAnswerCount() < 5) {
                        answer();
                    }
                    if (player.getCorrectAnswerCount() == 5) {
                        System.out.println("Congratulations " + player.getName() + " you have won the game!");
                    }
                }
        }
    }

    //prompting for name for new player
    private String promptName() {
        intro.showBanner();
        directions();
        String namePattern = "([a-zA-Z ]{2,20})";
        String name = prompter.prompt("Please enter your first and last name:", namePattern,
                "Invalid Data: Must be between 2-20 Characters (insensitive)\n").toLowerCase();
        System.out.println();
        player = new Player(name, Difficulty.BEGINNER);
        manager.playerExist(player);

        return name;
    }

   /* method() used by startRoundOfQuestions - this shows the current question, prompts for a response
    * then determines if correct. Sets field which determines whether or not to continue (continuesGame),
    * also gets and sets number of correct and incorrect answers from player class
    */
    private void answer() {
        int answerCorrectCount = player.getCorrectAnswerCount();
        int answerIncorrectCount = player.getIncorrectAnswerCount();
        Questions question = (player.getLevel()).nextQuestion();
        System.out.println(question);
        String answer = promptForAnswer();
        if (question.isCorrectAnswer(answer)) {
            player.setCorrectAnswerCount(answerCorrectCount += 1);
            System.out.println("\nGreat\n");
        } else {
            System.out.println("\nYour answer is wrong!\n");
            player.setIncorrectAnswerCount(answerIncorrectCount += 1);
        }

        if (player.shouldPlayerContinue()) {
            setContinueGame(true);
        } else {
            setContinueGame(false);
        }

        Console.pause(100);
        Console.clear();
        intro.showBanner();
    }

    private void directions() {
        String message = String.format("Directions: If you get 5 correct answers, you will level up! Get 3 wrong and you are out!\n");
        for (char c : message.toCharArray()) {
            System.out.print(c);
            Console.pause(50);
        }
    }

    private String promptForAnswer() {
        String answer = prompter.prompt("Please enter your answer:");
        return answer;
    }

    private boolean getContinueGame() {
        return continueGame;
    }

    private void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }
}