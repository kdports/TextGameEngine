package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecisionTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testSetTarget() {
        Decision d = new Decision("");
        Slide s = new Slide("test");
        d.setTarget(s);
        assertEquals(s, d.target);
    }

    @Test
    void testSetTargetNull() {
        Decision d = new Decision("");
        d.setTarget(null);
        assertNull(d.target);
    }
}