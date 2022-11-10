package com.testyourjavabeans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Difficulty {
    BEGINNER("easy"),
    INTERMEDIATE("medium"),
    ADVANCED("hard");

    private final List<Question> questions = new ArrayList<>();
    private String filename;

    Difficulty(String filename) {
        this.filename = filename;
        createQuestionBank();
    }

    //loads the questions into each enums by the usage of the constructor "filename"
    private void createQuestionBank() {
        List<String> lines;

        try {
            lines = Files.readAllLines((Path.of("questions/" + filename + "-questions.csv")));
            for (String line : lines) {
                String[] tokens = line.split(",");
                String questionType = line.split(",")[0];
                String question = tokens[2];
                String answer = tokens[3];
                if ("M".equals(questionType)) {
                    question = String.format("%s\n %s\n %s\n %s\n %s\n", tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                    answer = tokens[7].toLowerCase();
                } else if ("T".equals(questionType)) {
                    answer = tokens[3].toLowerCase();
                }
                questions.add(new Question(question, answer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //shuffle the questions and remove the question once it is asked (answered).
    public Question nextQuestion() { //refactor the name to singular
        Collections.shuffle(questions);
        return questions.remove(0);
    }
}