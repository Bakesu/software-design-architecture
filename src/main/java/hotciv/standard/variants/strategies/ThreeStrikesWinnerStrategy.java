package hotciv.standard.variants.strategies;

import hotciv.framework.Player;
import hotciv.framework.variants.strategies.WinnerStrategy;

import java.util.HashMap;


public class ThreeStrikesWinnerStrategy implements WinnerStrategy {
    private final HashMap<Player, Integer> battlesWon = new HashMap<>();
    @Override
    public Player getWinner(int age, HashMap cityPositions) {
        return battlesWon.keySet()
                .stream()
                .filter(p -> battlesWon.get(p) != null)
                .filter(p -> battlesWon.get(p) >= 3)
                .findFirst().orElse(null);
    }
    @Override
    public void incrementBattlesWonBy(Player p) {
        if(battlesWon.get(p) != null) {
            int currentWins = battlesWon.get(p);
            battlesWon.put(p, currentWins+1);
        } else {
            battlesWon.put(p,1);
        }
    }

    @Override
    public void incrementRoundsPlayed() { return; }


}
