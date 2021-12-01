package handlers;

import client.GuiDecision.GuiDecision;
import client.Main;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.IdControl;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DecisionHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDecisionHandlerEditMessage() {
        Studio s = new Studio();
        EditorGame e = new EditorGame();

        Decision d = new Decision("old text");
        DecisionHandler decisionHandler = new DecisionHandler(s, e);
        decisionHandler.editMessage(d, "new text");
        assertTrue(d.getText().equals("new text"));
    }
}