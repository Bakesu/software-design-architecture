package hotciv.standard.variants.strategies;

import hotciv.framework.Player;
import hotciv.framework.variants.strategies.WinnerStrategy;

import java.util.HashMap;

public class RedWinsIn3000BCWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(int age, HashMap cityPositions) {
        if (age == -3000) {
            return Player.RED;
        }
        return null;
    }

    @Override
    public void incrementBattlesWonBy(Player playerInTurn) {
        return;
    }

    @Override
    public void incrementRoundsPlayed() { return; }
}
