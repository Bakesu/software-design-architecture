package hotciv.standard.unitTests;

import hotciv.standard.variants.strategies.HundredYearsTimeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TestHundredYearsTimeStrategy {
    HundredYearsTimeStrategy hundredYearsTimeStrategy;

    @BeforeEach
    void setUp() {
        hundredYearsTimeStrategy = new HundredYearsTimeStrategy();
    }

    @Test
    void incrementAge() {
        assertThat(hundredYearsTimeStrategy.incrementAge(-4000), is(100));
        assertThat(hundredYearsTimeStrategy.incrementAge(-3100), is(100));
    }
}