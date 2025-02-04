package hotciv.standard.variants.strategies;

import hotciv.framework.variants.strategies.TimeStrategy;

public class VaryingTimeStrategy implements TimeStrategy {

    @Override
    public int incrementAge(int age) {
        if(age < -100){
            return 100;
        } else if(age == -100) {
            return 99;
        } else if(age == -1) {
            return 2;
        } else if(age == 1) {
            return 49;
        } else if(age < 1750){
            return 50;
        } else if(age < 1900){
            return 25;
        } else if(age < 1970){
            return 5;
        } else {return 1;
        }
    }
}
