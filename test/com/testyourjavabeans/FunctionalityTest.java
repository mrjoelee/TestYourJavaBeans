package com.testyourjavabeans;


import org.junit.Test;
//blah

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;


public class FunctionalityTest {


    // test that correct question file exists to begin game

    @Test
    public void playerExist_shouldReturnPlayer_ifPlayerPreviouslySaved() throws Exception {
        Player player1 = new Player("sarina", Difficulty.BEGINNER);
        player1.playerExist();
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

    @Test
    public void prompterShouldReadValuesFromFile() throws Exception {
        PlayerManager playerList = new PlayerManager("player/playerdata.csv");
        List<Player> players = playerList.upload();

        Player player1 = players.get(0);
        assertEquals(player1.getName(),"doug");
        assertEquals(player1.getLevel(), Difficulty.BEGINNER);


        Player player2 = players.get(1);
        assertEquals(player2.getName(), "jeff");
        assertEquals(player2.getLevel(), Difficulty.ADVANCED);
    }
}
