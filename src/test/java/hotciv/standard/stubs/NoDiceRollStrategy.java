package hotciv.standard.stubs;

import hotciv.framework.variants.strategies.DiceRollStrategy;

public class NoDiceRollStrategy implements DiceRollStrategy {
    private int attackerDice = 1;
    private int defenderDice = 1;
    @Override
    public int rollAttack() {
        return attackerDice;
    }

    @Override
    public int rollDefense(){
        return defenderDice;
    }

    public void setAttackerDice(int fixedRoll){
        attackerDice = fixedRoll;
    }

    public void setDefenderDice(int fixedRoll) {
        defenderDice = fixedRoll;
    }
}
