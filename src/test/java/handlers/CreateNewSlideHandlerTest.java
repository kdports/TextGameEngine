package handlers;

import client.Main;
import entities.EditorGame;
import entities.Game;
import entities.Studio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rdf.RDFLoadToStudio;
import utils.IdControl;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateNewSlideHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateNewSlideHandler() {
        /*
         * Should be called individually from RDFLoadTest!
         */
        Studio s = new Studio();
        EditorGame e = new EditorGame();
        String[] args = new String[] {} ;
        Main.main(args);
        // Please close the window that appears in order for the unit tests to complete!

        CreateNewSlideHandler slideHandler = new CreateNewSlideHandler(s, e);
        slideHandler.execute(0.0,0);
        assertTrue(IdControl.getId() == 1);
        assertTrue(e.getSlideMap().size() == 1);
    }
}