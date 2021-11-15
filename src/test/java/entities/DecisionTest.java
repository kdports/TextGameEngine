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
        Slide s = new Slide(1, "test");
        d.setTarget(s);
        assertEquals(s, d.target);
    }

    @Test
    void testSetTargetNull() {
        Decision d = new Decision("");
        d.setTarget(null);
        assertNull(d.target);
    }

    @Test
    void testSetOrigin() {
        Decision d = new Decision("");
        Slide s = new Slide(1, "");
        d.setOrigin(s);
        assertTrue(d.getOrigin() == s);
    }

    @Test
    void testSetTest() {
        Decision d = new Decision("");
        d.setText("help!");
        assertTrue(d.getText().equals("help!"));
    }

    @Test
    void testDecisionID() {
        Decision d = new Decision("");
        Slide s = new Slide(1, "");
        Decision d2 = new Decision("", s, 1);

        assertTrue(d.getId() == 0);
        assertTrue(d2.getId() == 1);
    }
}