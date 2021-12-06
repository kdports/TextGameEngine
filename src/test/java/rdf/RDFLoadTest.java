package rdf;

import client.DisplayGame.GameRenderer;
import client.Main;
import client.MainTitleScreen;
import entities.*;
//import rdf.RDFLoadToPlayer;
import javafx.application.Platform;
import rdf.RDFLoad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)

class RDFLoadTest {
    private final MainTitleScreen mainTitleScreen = new MainTitleScreen();
    @Start
    public void start(Stage stage) throws Exception {
        mainTitleScreen.start(stage);

        stage.show();
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
    void testRDFLoadToStudio(FxRobot robot) {
        /*
        * Should be called individually from CreateNewSlideHandlerTest!
        */
        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
        try {
            RDFLoadToStudio loader = new RDFLoadToStudio(rdfFilepath);
            EditorGame e = loader.loadEditorGameFromFile();
            robot.clickOn("Quit");
            // This test opens the studio and gamerenderer over the course of its function
            // Please close those windows for the test to complete!

            assertEquals(2, e.getSlideMap().size());
            assertEquals(2, e.getDecisionMap().size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}