package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

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

    @Test
    void testConditionals() {
        Decision d = new Decision("");
        Decision d2 = new Decision("");
        Decision d3 = new Decision("");
        HashSet<Decision> checker = new HashSet<>();
        HashSet<String> itemCheck = new HashSet<>();

        d.addToDecisionConditionals(d2);

        assertFalse(d.checkConditionals(checker, itemCheck));

        checker.add(d2);

        assertTrue(d.checkConditionals(checker, itemCheck));

        d.addToDecisionConditionals(d3);
        checker.add(d3);

        assertTrue(d.checkConditionals(checker, itemCheck));
    }

    @Test
    void testItemConditionals() {
        Decision d = new Decision("");
        String a = "a";
        String b = "b";
        HashSet<Decision> checker = new HashSet<>();
        HashSet<String> itemCheck = new HashSet<>();

        d.addToItemConditionals(a);

        assertFalse(d.checkConditionals(checker, itemCheck));

        itemCheck.add(a);

        assertTrue(d.checkConditionals(checker, itemCheck));

        d.addToItemConditionals(b);
        itemCheck.add(b);

        assertTrue(d.checkConditionals(checker, itemCheck));
    }
}