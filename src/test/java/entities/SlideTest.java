package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SlideTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addDecision() {
        Decision d = new Decision("test");
        Slide s = new Slide("", new ArrayList<>(), a2);
        assert s.addDecision(d);
        ArrayList<Decision> ar = new ArrayList<>();
        ar.add(d);
        assertEquals(s.decisions, ar);
    }
}