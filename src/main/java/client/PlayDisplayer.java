package client;
import entities.Player;

/**
 * The displayer that is used to display the game
 */
public interface PlayDisplayer {
    /**
     * Displays the game
     */
    void display();

    /**
     * Sets the game player
     * @param player The player to be used as the game player
     */
    void setPlayer(Player player);
}
