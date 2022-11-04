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

    private final List<Questions> questions = new ArrayList<>();

    Difficulty(String filename) {

        List<String> lines;

        {
            try {
                lines = Files.readAllLines((Path.of("questions/easy-questions.csv")));
                for(String line : lines){
                    String[] tokens = line.split(",");
                    Integer number = Integer.valueOf(tokens[0]);
                    String question = tokens[1];
                    String answer = tokens[2];
                    String answer1 = tokens[3];
                    String answer2 = tokens[4];
                    String answer3 = tokens[5];

                    //Todo add.

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.shuffle(questions);
    }

    public Questions nextQuestion() {
        return questions.remove(0);
    }


    //maybe we need to implement the List and the Files.ReadAlline
}
