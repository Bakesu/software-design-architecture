package hotciv.framework.variants.strategies;

import hotciv.framework.*;

import java.util.HashMap;

public interface WinnerStrategy {
    Player getWinner(int age, HashMap cityPositions);

    void incrementBattlesWonBy(Player playerInTurn);

    void incrementRoundsPlayed();
}
