package handlers;

import client.Main;
import client.ThemeColours;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.IdControl;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewDecisionHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateNewDecisionHandler() {
        Studio s = new Studio();
        EditorGame e = new EditorGame();
        // Please close the window that appears in order for the unit tests to complete!

        Slide sl = new Slide(5, "");
        CreateNewDecisionHandler decisionHandler = new CreateNewDecisionHandler(s, e);
        decisionHandler.testCreate(sl, 500, 500, new ThemeColours());
        assertTrue(e.getDecisionMap().size() == 1);
    }
}