package hotciv.standard.variants.strategies;

import hotciv.framework.variants.strategies.TimeStrategy;

public class HundredYearsTimeStrategy implements TimeStrategy {
    @Override
    public int incrementAge(int age) {
        return 100;
    }
}
