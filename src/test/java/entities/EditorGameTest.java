package entities;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EditorGameTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testSetDecisionMap() {
        EditorGame e = new EditorGame();
        Decision d = new Decision("");
        GuiDecision g = new GuiDecision("");

        assertTrue(e.getDecisionMap().isEmpty());

        e.connectDecisionAndRenderableDecision(d, g);

        assertTrue(e.getDecisionMap().size() == 1);

        Decision d2 = new Decision("");
        GuiDecision g2 = new GuiDecision("");

        e.connectDecisionAndRenderableDecision(d2, g2);

        assertTrue(e.getDecisionMap().size() == 2);
    }

    @Test
    void testSetSlideMap() {
        EditorGame e = new EditorGame();
        Slide s = new Slide(1, "");
        GuiSlide gs = new GuiSlide("");

        assertTrue(e.getSlideMap().isEmpty());

        e.connectSlideAndRenderableSlide(s, gs);

        assertTrue(e.getSlideMap().size() == 1);

        Slide s2 = new Slide(2, "");
        GuiSlide gs2 = new GuiSlide("");

        e.connectSlideAndRenderableSlide(s2, gs2);

        assertTrue(e.getSlideMap().size() == 2);
    }

    @Test
    void testSetFirstSlide() {
        EditorGame e = new EditorGame();
        Slide s = new Slide(1, "");

        assertTrue(e.firstSlide == null);

        e.setFirstSlide(s);

        assertTrue(e.firstSlide == s);
        assertTrue(s.getObservableFirstSlide().getValue() == true);

        e.deleteSlide(s);

        assertTrue(e.firstSlide == null);
    }

    @Test
    void testSetClearAll() {
        EditorGame e = new EditorGame();
        Slide s = new Slide(1, "");
        Slide s2 = new Slide(2, "");
        GuiSlide gs = new GuiSlide("");

        e.setFirstSlide(s);
        e.connectSlideAndRenderableSlide(s2, gs);

        e.clearAll();

        assertTrue(e.getSlideMap().isEmpty());
    }

    @Test
    void testSetInvalidMap() {
        EditorGame e = new EditorGame();
        Decision d = new Decision("");
        GuiDecision g = new GuiDecision("");
        GuiDecision g2 = new GuiDecision("");

        e.connectDecisionAndRenderableDecision(d, g);
        e.connectDecisionAndRenderableDecision(d, g2);

        assertTrue(e.getDecisionMap().size() == 1);
    }
}