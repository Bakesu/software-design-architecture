package hotciv.standard.variants.strategies;

import hotciv.framework.Player;
import hotciv.framework.variants.strategies.WinnerStrategy;

import java.util.HashMap;


public class CityConquestThenThreeStrikesWinnerStrategy implements WinnerStrategy {
    private int roundsPlayed = 1;
    private final int stateChangeRound = 20;
    private final WinnerStrategy threeStrikesWinnerStrategy;
    private final WinnerStrategy cityConquestWinnerStrategy;

    public CityConquestThenThreeStrikesWinnerStrategy() {
        threeStrikesWinnerStrategy = new ThreeStrikesWinnerStrategy();
        cityConquestWinnerStrategy = new CityConquestWinnerStrategy();

    }

    @Override
    public Player getWinner(int age, HashMap cityPositions) {
        if(roundsPlayed <= stateChangeRound) {
            return cityConquestWinnerStrategy.getWinner(age, cityPositions);
        }
        return threeStrikesWinnerStrategy.getWinner(age, cityPositions);
    }

    @Override
    public void incrementBattlesWonBy(Player playerInTurn) {
        if (roundsPlayed <= stateChangeRound) {
            cityConquestWinnerStrategy.incrementBattlesWonBy(playerInTurn);
        } else {
            threeStrikesWinnerStrategy.incrementBattlesWonBy(playerInTurn);
        }
    }

    @Override
    public void incrementRoundsPlayed() {
        roundsPlayed++;
    }
}
