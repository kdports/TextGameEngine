package client;

import entities.Player;
import javafx.stage.Stage;

/**
 * The displayer that is used to display the game
 */
public interface PlayDisplayer {

    /**
     * Method to launch the displayer
     * @param args String to use to launc
     */
    void begin(String[] args);

    /**
     * Sets the stage for the game
     */
    void setStage(Stage stage);

    /**
     * Displays the game
     */
    void display();

    /**
     * Sets the game player
     *
     * @param player The player to be used as the game player
     */
    void setPlayer(Player player);
}
