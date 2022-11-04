package com.testyourjavabeans;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public enum Difficulty {
    BEGINNER("easy"),
    INTERMEDIATE("medium"),
    ADVANCED("hard");

    Difficulty(String filename) {
    }
    //maybe we need to implement the List and the Files.ReadAlline
}
