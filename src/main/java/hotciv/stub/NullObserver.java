package hotciv.stub;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class NullObserver implements GameObserver {

    @Override
    public void worldChangedAt(Position pos) {
        return;
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        return;
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        return;
    }
}
