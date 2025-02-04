package hotciv.framework.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface BattleStrategy {
    boolean attackingUnitWins(Game game, Position from, Position to);
}
