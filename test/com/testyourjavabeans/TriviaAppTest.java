package com.testyourjavabeans;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TriviaAppTest {
    @Test
    public void prompterShouldReadValuesFromFile() throws Exception {
        PlayerManager playerList = new PlayerManager("player/playerdata.csv");
        List<Player> players = playerList.upload();

        Player player1 = players.get(0);
        assertEquals(player1.getName(),"test1");
        assertEquals(player1.getLevel(), Difficulty.BEGINNER);


        Player player2 = players.get(1);
        assertEquals(player2.getName(), "test2");
        assertEquals(player2.getLevel(), Difficulty.BEGINNER);
    }
}