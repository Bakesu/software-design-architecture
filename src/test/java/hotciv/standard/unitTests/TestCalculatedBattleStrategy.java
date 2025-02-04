package hotciv.standard.unitTests;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.stubs.GameStubForBattleTesting;
import hotciv.standard.variants.strategies.CalculatedBattleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TestCalculatedBattleStrategy {

    private Iterator<Position> iter;
    private List<Position> neighborhood;
    private Position center;

    Game game;
    @BeforeEach
    public void setUp() {
        game = new GameStubForBattleTesting();
    }

    @Test
    public void shouldGiveCorrectTerrainFactors() {
        // plains have multiplier 1
        assertThat(CalculatedBattleStrategy.getTerrainFactor(game, new Position(0,1)), is(1));
        // hills have multiplier 2
        assertThat(CalculatedBattleStrategy.getTerrainFactor(game, new Position(1,0)), is(2));
        // forest have multiplier 2
        assertThat(CalculatedBattleStrategy.getTerrainFactor(game, new Position(0,0)), is(2));
        // cities have multiplier 3
        assertThat(CalculatedBattleStrategy.getTerrainFactor(game, new Position(1,1)), is(3));
    }

    @Test
    public void shouldGiveSum1ForBlueAtP5_5() {
        assertThat("Blue unit at (5,5) should get +1 support",
                CalculatedBattleStrategy.getFriendlySupport( game, new Position(5,5), Player.BLUE), is(+1));
    }

    @Test
    public void shouldGiveSum0ForBlueAtP2_4() {
        assertThat("Blue unit at (2,4) should get +0 support",
                CalculatedBattleStrategy.getFriendlySupport( game, new Position(2,4), Player.BLUE), is(+0));
    }
    @Test
    public void shouldGiveSum2ForRedAtP2_4() {
        assertThat("Red unit at (2,4) should get +2 support",
                CalculatedBattleStrategy.getFriendlySupport( game, new Position(2,4), Player.RED), is(+2));
    }
    @Test
    public void shouldGiveSum3ForRedAtP2_2() {
        assertThat("Red unit at (2,2) should get +3 support",
                CalculatedBattleStrategy.getFriendlySupport( game, new Position(2,2), Player.RED), is(+3));
    }
}