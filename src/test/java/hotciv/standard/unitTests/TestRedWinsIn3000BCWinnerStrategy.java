package hotciv.standard.unitTests;

import hotciv.framework.Player;
import hotciv.standard.variants.strategies.RedWinsIn3000BCWinnerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class TestRedWinsIn3000BCWinnerStrategy {
    RedWinsIn3000BCWinnerStrategy redWinsIn3000BCWinnerStrategy;
    @BeforeEach
    void setUp() {
        redWinsIn3000BCWinnerStrategy = new RedWinsIn3000BCWinnerStrategy();
    }

    @Test
    void getWinner() {
        assertThat(redWinsIn3000BCWinnerStrategy.getWinner(-4000,new HashMap()), is(nullValue()));
        assertThat(redWinsIn3000BCWinnerStrategy.getWinner(-3000,new HashMap()), is(Player.RED));
    }
}