package hotciv.standard.unitTests;

import hotciv.standard.variants.strategies.VaryingTimeStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TestVaryingTimeStrategy {
    VaryingTimeStrategy varyingTimeStrategy;
    @BeforeEach
    void setUp() {
        varyingTimeStrategy = new VaryingTimeStrategy();

    }

    @Test
    void incrementAgeTest() {
        assertThat(varyingTimeStrategy.incrementAge(-4000), is(100));
        assertThat(varyingTimeStrategy.incrementAge(-100), is(99));
        assertThat(varyingTimeStrategy.incrementAge(-1), is(2));
        assertThat(varyingTimeStrategy.incrementAge(1), is(49));
        assertThat(varyingTimeStrategy.incrementAge(50), is(50));
        assertThat(varyingTimeStrategy.incrementAge(100), is(50));
        assertThat(varyingTimeStrategy.incrementAge(1750), is(25));
        assertThat(varyingTimeStrategy.incrementAge(1900), is(5));
        assertThat(varyingTimeStrategy.incrementAge(1970), is(1));
        assertThat(varyingTimeStrategy.incrementAge(1971), is(1));
    }
}