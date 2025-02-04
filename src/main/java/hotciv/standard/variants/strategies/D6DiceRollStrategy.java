package hotciv.standard.variants.strategies;

import hotciv.framework.variants.strategies.DiceRollStrategy;

public class D6DiceRollStrategy implements DiceRollStrategy {
    @Override
    public int rollAttack() {
        return roll();
    }
    @Override
    public int rollDefense(){
        return roll();
    }

    public int roll(){
        return (int) Math.floor((Math.random()*6)+1);
    }
}


