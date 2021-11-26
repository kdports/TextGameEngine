package client.DisplayGame;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import entities.Player;

public class CreateTextPanel {

    /**
     * The method creates a main text area for the game screen.
     *
     * @param player - The player of the game
     * @return - The text area.
     */
    public static TextArea createTextArea(Player player) {
        TextArea text = new TextArea();

        text.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD,FontPosture.REGULAR,20));
        text.setText(player.currentSlide.getPrompt());
        text.setEditable(false);
        text.setWrapText(true);
        VBox.setVgrow(text, Priority.ALWAYS);

        return text;
    }
}