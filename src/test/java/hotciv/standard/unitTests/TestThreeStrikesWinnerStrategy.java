package hotciv.standard.unitTests;

import hotciv.framework.Player;
import hotciv.standard.variants.strategies.ThreeStrikesWinnerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class TestThreeStrikesWinnerStrategy {
    ThreeStrikesWinnerStrategy threeStrikesWinnerStrategy;
    @BeforeEach
    void setUp() {
        threeStrikesWinnerStrategy = new ThreeStrikesWinnerStrategy();
    }

    @Test
    void testGetWinner() {
       assertThat(threeStrikesWinnerStrategy.getWinner(3000, new HashMap()), is(nullValue()));
       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.RED);
       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.RED);
       assertThat(threeStrikesWinnerStrategy.getWinner(3000,new HashMap()), is(nullValue()));

       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.RED);
       assertThat(threeStrikesWinnerStrategy.getWinner(3000, new HashMap()), is(Player.RED));

       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.RED);
       assertThat(threeStrikesWinnerStrategy.getWinner(3000, new HashMap()), is(Player.RED));

       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.BLUE);
       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.BLUE);
       threeStrikesWinnerStrategy.incrementBattlesWonBy(Player.BLUE);

       //assertThat(threeStrikesWinnerStrategy.getWinner(3000, new HashMap()), is(nullValue()));


    }
}