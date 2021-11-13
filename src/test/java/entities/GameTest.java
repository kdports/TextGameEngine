package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddSlide() {
        Game g = new Game();
        Slide s = new Slide(1, "test");
        assert g.addSlide(s);
        assert g.getSlides().contains(s);
    }

    @Test
    void testSetFirstSlide() {
        Game g = new Game();
        Slide s = new Slide(1, "test");
        assert g.setFirstSlide(s);
        assertEquals(s, g.firstSlide);
    }

    @Test
    void testSetFirstSlideNull() {
        Game g = new Game();
        assert g.setFirstSlide(null);
        assertNull(g.firstSlide);
    }

}