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
                lines = Files.readAllLines((Path.of("questions/questions.csv")));
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
