package rdf;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import client.Main;
import entities.Decision;
import entities.EditorGame;
import entities.Game;
import entities.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RDFSaveTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testRDFSave() {
        String rdfSavePath = "src/main/resources/rdf/saved_game.ttl";

        EditorGame e = new EditorGame();

        Slide s = new Slide(1, "This is a saved game!");
        Slide s2 = new Slide(2, "This is a saved game!");
        GuiSlide gs = new GuiSlide("");
        GuiSlide gs2 = new GuiSlide("");
        GuiDecision g = new GuiDecision("");
        Decision d = new Decision("", s, 1, s2);

        e.setFirstSlide(s);

        e.connectDecisionAndRenderableDecision(d, g);
        e.connectSlideAndRenderableSlide(s, gs);
        e.connectSlideAndRenderableSlide(s2, gs2);

        RDFSave save = new RDFSave();
        try {
            save.saveToTrig(e, rdfSavePath);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}