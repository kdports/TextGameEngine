package client.DisplayGame;

import client.Theme;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import static java.lang.Long.MAX_VALUE;

public class CreateButtons{
    Player player;
    Theme theme;

    /**
     * Creates the panel with all the buttons to add to the screen.
     *
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme  - The theme that the GameRenderer is currently using
     */
    CreateButtons(Player player, Theme theme) {
        this.theme = theme;
        this.player = player;
    }

    /**
     * Creates 1 button that is styled as one of the decisions for a slide.
     *
     * @return - The button.
     */
    private static Button createButton() {
        // Creates a button and sets the theme, font, image, etc of the button.
        Button b = new Button();
        b.setMaxWidth(MAX_VALUE);
        b.setBorder(null);
        b.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR, 15));

        return b;
    }

    /**
     * Creates buttons for all the decisions in a slide.
     *
     * @return - The number of buttons.
     */
    public static int createButtons(Player player, VBox root) {
        // Keeps count of how many buttons are in each Slide
        int count = 0;
        // Gets the two images that are used in the buttons
//        final ImageIcon arrow = createIcon("redArrow.png", theme.textColor);
//        final ImageIcon redArrow = createIcon("redArrow.png", theme.textHoverColor);

        // If this is the last slide then create the restart button
        if (createRestartButton(player, root)){
            return 1;
        }
        // Creates the other buttons if it is not the last slide
        for (int i = 0; i < player.currentValidDecisions.size(); i++) {
            count++;

            Button b = createButton();
            b.setText(player.currentValidDecisions.get(i).text);
            addDestinationAction(player, b, i);
//            addListeners(b, redArrow, arrow);
            root.getChildren().add(b);
        }
        return count;
    }

    /**
     * Creates the restart button when the last slide is reached
     *
     * @return - The number of buttons.
     */
    public static boolean createRestartButton(Player player, VBox root){
        // Checks if there are any more decisions, if not then it creates the replay buttons
        // and returns true
        if (player.currentValidDecisions.size() == 0) {
            Button b = createButton();
            b.setText("Replay the game?");
//            addListeners(b, redArrow, arrow);
            b.setOnAction(arg0 -> {
                player.currentSlide = player.game.firstSlide;
                player.playScene();
            });
            root.getChildren().add(b);
            return true;
        }
        return false;
    }

    /**
     * Adds which slide to go to when the button is clicked.
     *
     * @param button - The button that will be clicked.
     * @param index - The index of the decision linked to the button.
     */
    private static void addDestinationAction(Player player, Button button, int index) {
        button.setOnAction(arg0 -> {
            player.currentSlide = player.currentValidDecisions.get(index).target;
            player.playScene();
        });
    }
}