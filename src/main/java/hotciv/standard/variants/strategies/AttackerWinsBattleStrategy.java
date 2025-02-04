package hotciv.standard.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.variants.strategies.BattleStrategy;
import hotciv.standard.GameImpl;

public class AttackerWinsBattleStrategy implements BattleStrategy {
    @Override
    public boolean attackingUnitWins(Game game, Position from, Position to) {
        return true;
    }
}
