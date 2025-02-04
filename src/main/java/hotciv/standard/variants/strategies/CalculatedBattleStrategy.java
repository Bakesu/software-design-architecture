package hotciv.standard.variants.strategies;

import hotciv.framework.*;
import hotciv.framework.variants.strategies.BattleStrategy;
import hotciv.framework.variants.strategies.DiceRollStrategy;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;

import java.util.Iterator;

public class CalculatedBattleStrategy implements BattleStrategy {
    DiceRollStrategy diceRollStrategy;

    public CalculatedBattleStrategy(DiceRollStrategy diceRollStrategy) {
        this.diceRollStrategy = diceRollStrategy;
    }


    @Override
    public boolean attackingUnitWins(Game game, Position from, Position to) {
        int attackerStrength = calculateCombinedStrength(game, from, game.getUnitAt(from).getOwner());
        int defenderStrength = calculateCombinedStrength(game, to, game.getUnitAt(to).getOwner());

        int d1 = diceRollStrategy.rollAttack();
        int d2 = diceRollStrategy.rollDefense();
        return attackerStrength * d1 > defenderStrength * d2;
    }

    private int calculateCombinedStrength(Game game, Position position, Player player){
        return (getUnitStrength(game, position) + getFriendlySupport(game, position, player)) * getTerrainFactor(game, position);
    }

    private int getUnitStrength(Game game, Position position){
        if (game.getUnitAt(position).getOwner().equals(game.getPlayerInTurn())){
            return game.getUnitAt(position).getAttackingStrength();
        }
        return game.getUnitAt(position).getDefensiveStrength();
    }

    public static int getTerrainFactor(Game game, Position position) {
        // cities overrule underlying terrain
        if ( game.getCityAt(position) != null ) { return 3; }
        Tile t = game.getTileAt(position);
        if (t.getTypeString().equals(GameConstants.FOREST) ||
                t.getTypeString().equals(GameConstants.HILLS)) {
            return 2;
        }
        return 1;
    }

    public static int getFriendlySupport(Game game, Position position, Player player) {
        Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
        Position p;
        int support = 0;
        while ( neighborhood.hasNext() ) {
            p = neighborhood.next();
            if ( game.getUnitAt(p) != null &&
                    game.getUnitAt(p).getOwner() == player ) {
                support++;
            }
        }
        return support;
    }
}
