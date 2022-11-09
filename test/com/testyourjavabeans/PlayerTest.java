package com.testyourjavabeans;


import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.Assert.*;


public class PlayerTest {
    PlayerManager manager;

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
        Player player1 = new Player("sarina", Difficulty.BEGINNER);
        Files.readAllLines(Path.of("player/playerdata.csv"));

        assertEquals(player1.getLevel(), Difficulty.BEGINNER);
    }

    @Test
    public void playerExist_getLevel_shouldThrowExceptionIfPlayerDoesNotExist() throws Exception {
        // test that paired level returns to appropriate entered name
        Files.readAllLines(Path.of("player/playerdata.csv"));
    }
}