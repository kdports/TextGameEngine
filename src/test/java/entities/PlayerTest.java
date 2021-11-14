package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @BeforeEach
    void setUp() {
    }

    // Currently just a placeholder, change once the function in Player is fully implemented.
    @Test
    void testCheckChoiceTrue() {
        Decision d = new Decision("test");
        assertTrue(Player.checkChoice(d));
    }


}