package handlers;

import client.GuiSlide.GuiSlide;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SlideHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSlideHandlerEditMessage() {
        Studio s = new Studio();
        EditorGame e = new EditorGame();

        Slide sl = new Slide(1, "old next");
        SlideHandler slideHandler = new SlideHandler(s, e);
        slideHandler.editMessage(sl, "new text");
        assertTrue(sl.getPrompt().equals("new text"));
    }

    @Test
    void testSlideHandlerSetMain() {
        Studio s = new Studio();
        EditorGame e = new EditorGame();

        Slide sl = new Slide(1, "old next");
        SlideHandler slideHandler = new SlideHandler(s, e);
        slideHandler.setMain(sl);
        assertTrue(e.firstSlide == sl);
        assertTrue(sl.getObservableFirstSlide().getValue());
    }

    @Test
    void testSlideHandlerDeleteSlide() {
        Studio s = new Studio();
        EditorGame e = new EditorGame();

        Slide sl = new Slide(1, "old next");
        GuiSlide gs = new GuiSlide("");
        SlideHandler slideHandler = new SlideHandler(s, e);
        slideHandler.setMain(sl);

        e.connectSlideAndRenderableSlide(sl, gs);

        slideHandler.delete(sl);

        assertTrue(e.getSlideMap().isEmpty());
        assertTrue(e.deletedSlideMapProperty().size() == 1);
        assertTrue(e.firstSlide == null);
    }
}