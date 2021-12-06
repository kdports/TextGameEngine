package entities;

import client.DisplayGame.GameRenderer;
import client.ThemeColours;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

/*    private GameRenderer renderer;
    private Player p;
    private Game sampleGame;*/

    @BeforeEach
    void setUp() {
    }

    // Additonal tests were added in phase 1 for the expanded functionality of Player
    @Test
    void testCheckChoiceTrue() {
        Decision d = new Decision("test");
        Game sampleGame = CreateSampleGame.returnGame();
        Player p = new Player(sampleGame);
        assertTrue(p.checkChoice(d));
    }

    @Test
    void testFirstSlideStart() {
        Game sampleGame = CreateSampleGame.returnGame();
        Player p = new Player(sampleGame);
        p.playGame();
        assertTrue(p.currentSlide.getPrompt().equals(sampleGame.firstSlide.getPrompt()));
    }

    @Test
    void testCheckValidChoices() {
        Game sampleGame = CreateSampleGame.returnGame();
        Player p = new Player(sampleGame);
        p.playGame();
        p.checkValidChoices();
        assertTrue(p.currentValidDecisions.size() == 2);
    }

    @Test
    void testGetPastChosenDecisions() {
        Game sampleGame = CreateSampleGame.returnGame();
        Decision d = new Decision("test");
        Player p = new Player(sampleGame);
        p.playGame();

        assertTrue(p.GetPastChosenDecisions().isEmpty());

        p.AddToPastChosenDecisions(d);
        assertTrue(p.IsInPastChosenDecisions(d));
        assertTrue(!p.GetPastChosenDecisions().isEmpty());
    }
}