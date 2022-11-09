package com.testyourjavabeans;


import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.Assert.*;


public class PlayerTest {
    PlayerManager manager = new PlayerManager("player/playerdata.csv");

    @Test
    public void playerExist_shouldReturnPlayer_ifPlayerPreviouslySaved() throws Exception {
        Player player1 = new Player("sarina", Difficulty.BEGINNER);
        manager.playerExist(player1);
        Files.readAllLines(Path.of("player/playerdata.csv"));

        assertEquals(player1.getName(), "sarina");
    }

    @Test
    public void playerExist_getLevel_shouldReturnLevel_ifPlayerHasPlayedBefore() throws Exception {
        // test that paired level returns to appropriate entered name
        Player player1 = new Player("bon jovi", Difficulty.BEGINNER);
        Files.readAllLines(Path.of("player/playerdata.csv"));

        assertEquals(player1.getLevel(), Difficulty.BEGINNER);
        System.out.println(player1.getLevel());
    }

    @Test
    public void playerExist_getLevel_shouldThrowExceptionIfPlayerDoesNotExist() throws Exception {
        // test that paired level returns to appropriate entered name
        Player player1 = new Player("", Difficulty.BEGINNER);
        Files.readAllLines(Path.of("player/playerdata.csv"));

        assertEquals(player1.getName(), Difficulty.BEGINNER);
    }
}
