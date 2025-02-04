package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import java.util.HashMap;
import java.util.UUID;

public class UnitImpl implements Unit {
    private final String type;
    private final Player owner;
    private int moveCount;
    private boolean isFortified;
    private final String id;
    private static final HashMap<String, Integer> unitMoveCounts = new HashMap(){{
        put(GameConstants.ARCHER, GameConstants.ARCHER_MOVES);
        put(GameConstants.LEGION, GameConstants.LEGION_MOVES);
        put(GameConstants.SETTLER, GameConstants.SETTLER_MOVES);
        put(GameConstants.CARAVAN, GameConstants.CARAVAN_MOVES);
    }};
    private static final HashMap<String, Integer> unitAttackStrengths = new HashMap() {{
        put(GameConstants.ARCHER, GameConstants.ARCHER_ATTACK);
        put(GameConstants.LEGION, GameConstants.LEGION_ATTACK);
        put(GameConstants.SETTLER, GameConstants.SETTLER_ATTACK);
        put(GameConstants.CARAVAN, GameConstants.CARAVAN_ATTACK);
    }};
    private static final HashMap<String, Integer> unitDefensiveStrengths = new HashMap() {{
        put(GameConstants.ARCHER, GameConstants.ARCHER_DEFENSE);
        put(GameConstants.LEGION, GameConstants.LEGION_DEFENSE);
        put(GameConstants.SETTLER, GameConstants.SETTLER_DEFENSE);
        put(GameConstants.CARAVAN, GameConstants.CARAVAN_DEFENSE);
    }};

    public UnitImpl(Player owner, String type) {
        this.owner = owner;
        this.type = type;
        moveCount = unitMoveCounts.get(type);
        isFortified = false;
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    public void reduceMoveCount() {
        moveCount--;
    }

    public void resetMoveCount() {
        moveCount = unitMoveCounts.get(type);
    }

    @Override
    public int getDefensiveStrength() {
        if (isFortified && type.equals(GameConstants.ARCHER)) {
            return unitDefensiveStrengths.get(type) * 2;
        }
        return unitDefensiveStrengths.get(type);
    }

    @Override
    public int getAttackingStrength() {
        return unitAttackStrengths.get(type);
    }

    @Override
    public String getId() {
        return id;
    }

    public boolean isFortified(){
        return isFortified;
    }

    public void fortify(){
        isFortified = true;
    }

    public void breakFortification(){
        isFortified = false;
    }
}
