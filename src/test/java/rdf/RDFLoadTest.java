package rdf;

import client.DisplayGame.GameRenderer;
import client.Main;
import entities.*;
//import rdf.RDFLoadToPlayer;
import rdf.RDFLoad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RDFLoadTest {

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void testRDFLoadToPlayer() {
//        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
//        try {
//            RDFLoadToPlayer loader = new RDFLoadToPlayer(rdfFilepath);
//            Game game = loader.loadGameFromFile();
//            loader.sendGame(game);
//
//            assertTrue(game.firstSlide.getPrompt().equals("This is crazy! You are in a game!"));
//            assertTrue(game.firstSlide.outgoingDecisions.size() == 2);
//            assertTrue(game.getSlides().size() == 2);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    void testRDFLoadToStudio() {
        /*
        * Should be called individually from CreateNewSlideHandlerTest!
        */
        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
        try {
            String[] args = new String[] {} ;
            Main.main(args);
            RDFLoadToStudio loader = new RDFLoadToStudio(rdfFilepath);
            EditorGame e = loader.loadEditorGameFromFile();
            // This test opens the studio and gamerenderer over the course of its function
            // Please close those windows for the test to complete!

            assertEquals(2, e.getSlideMap().size());
            assertEquals(4, e.getDecisionMap().size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}