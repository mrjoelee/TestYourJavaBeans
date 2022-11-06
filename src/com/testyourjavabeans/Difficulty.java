package com.testyourjavabeans;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Difficulty {
    BEGINNER("easy"),
    INTERMEDIATE("medium"),
    ADVANCED("hard");

    private final List<Questions> questions = new ArrayList<>();
    String filename;
    int lineCounter;


    Difficulty(String filename) {
        this.filename = filename;
    }

    public void createQuestionBank() {
        List<String> lines;

        try {
            lines = Files.readAllLines((Path.of("questions/" + filename + "-questions.csv")));
            for (String line : lines) {
                String questionType = line.split(",")[0];
                int numberQuestion;

                switch (questionType) {
                    case "I":
                        String[] tokens = line.split(",");
                        numberQuestion = Integer.parseInt(tokens[1]);
                        String question = tokens[2];
                        String answer =  tokens[3];
                        questions.add(new Questions(question,answer));
                        break;
                    case "T":
                        tokens = line.split(",");
                        numberQuestion = Integer.parseInt(tokens[1]);
                        question = tokens[2];
                        answer =  tokens[3];
                        questions.add(new Questions(question,answer));
                        break;
                    case "M":
                        tokens = line.split(",");
                        numberQuestion = Integer.parseInt(tokens[1]);
                        question = String.format("%s, %s, %s, %s, %s", tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
                        answer = tokens[7];
                        questions.add(new Questions(question,answer));
                        answer =  tokens[7];
                        break;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Questions nextQuestion() {
        Collections.shuffle(questions);
        return questions.remove(0);
    }

    public int getListSize() {
        int result = 0;
        return questions.size();
    }
    //maybe we need to implement the List and the Files.ReadAlline
}
