package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.variants.factories.DeltaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TestDeltaCiv {
private GameImpl game;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new DeltaCivFactory());
    }

    @Test
    public void checkWorldLayout(){
        assertThat(game.getTileAt(new Position(5, 6)).getTypeString(), is(GameConstants.PLAINS));
        assertThat(game.getTileAt(new Position(1, 9)).getTypeString(), is(GameConstants.FOREST));
        assertThat(game.getTileAt(new Position(0, 5)).getTypeString(), is(GameConstants.MOUNTAINS));
        assertThat(game.getTileAt(new Position(1, 3)).getTypeString(), is(GameConstants.HILLS));
    }
}