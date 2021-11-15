package entities;

import client.GameRenderer;
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
        assertTrue(Player.checkChoice(d));
    }

    @Test
    void testFirstSlideStart() {
        Game sampleGame = CreateSampleGame.returnGame();
        GameRenderer renderer = new GameRenderer();
        Player p = new Player(renderer, sampleGame);
        p.playGame();
        assertTrue(p.currentSlide.getPrompt().equals(sampleGame.firstSlide.getPrompt()));
    }

    @Test
    void testCheckValidChoices() {
        Game sampleGame = CreateSampleGame.returnGame();
        GameRenderer renderer = new GameRenderer();
        Player p = new Player(renderer, sampleGame);
        p.playGame();
        p.checkValidChoices();
        assertTrue(p.currentValidDecisions.size() == 2);
    }
}