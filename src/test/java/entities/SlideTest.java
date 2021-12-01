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
        Slide s = new Slide(1,"");

        assertTrue(s.outgoingDecisions.isEmpty());

        assert s.addDecision(d);
        ArrayList<Decision> ar = new ArrayList<>();
        ar.add(d);
        assertEquals(s.outgoingDecisions, ar);

        s.removeDecision(d);
        ArrayList<Decision> ar2 = new ArrayList<>();
        assertEquals(s.outgoingDecisions, ar2);
    }

    @Test
    void setPrompt() {
        Slide s = new Slide(1,"");
        s.setPrompt("oh no");
        assertTrue(s.getPrompt().equals("oh no"));
    }

    @Test
    void setFirstSlide() {
        Slide s = new Slide(1,"");

        assertFalse(s.getObservableFirstSlide().getValue());

        s.setAsFirstSlide(true);
        assertTrue(s.getObservableFirstSlide().getValue());
    }
}