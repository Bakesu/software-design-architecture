package hotciv.standard.variants.strategies;

import hotciv.framework.Player;
import hotciv.framework.variants.strategies.WinnerStrategy;
import hotciv.standard.CityImpl;

import java.util.HashMap;

public class CityConquestWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(int age, HashMap cityPositions) {
        Player winner = null;
        for(Object p : cityPositions.keySet()) {
            CityImpl city = (CityImpl) cityPositions.get(p);
            if (winner == null || city.getOwner().equals(winner))
                winner = city.getOwner();
            else {
                winner = null;
            }
        }
        return winner;
    }

    @Override
    public void incrementBattlesWonBy(Player playerInTurn) {
        return;
    }

    @Override
    public void incrementRoundsPlayed() { return; }

}
