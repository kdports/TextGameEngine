package client.DisplayGame;

import client.Theme;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import static java.lang.Long.MAX_VALUE;

public class CreateButtons extends CreateButtonPane{


    /**
     * Creates the panel with all the buttons to add to the screen.
     *
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme  - The theme that the GameRenderer is currently using
     */
    CreateButtons(Player player, Theme theme) {
        super(player, theme);
    }

    /**
     * Creates 1 button that is styled as one of the decisions for a slide.
     *
     * @return - The button.
     */
    private Button createButton(ImageView arrow) {
        // Creates a button and sets the theme, font, image, etc of the button.
        Button b = new Button();
        b.setMaxWidth(MAX_VALUE);
        b.setBorder(null);
        b.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR, 25));


        b.setGraphic(arrow);
        b.setAlignment(Pos.CENTER_LEFT);
        b.setWrapText(true);
        b.setStyle("-fx-background-color:" + theme.backgroundColor + "; -fx-border-width: 0px;" +
                "-fx-text-fill: " + theme.textColor);

        b.setCursor(Cursor.HAND);
        return b;
    }

    /**
     * Creates buttons for all the decisions in a slide.
     *
     * @return - The number of buttons.
     */
    public int createButtons(Pane root) {
        // Keeps count of how many buttons are in each Slide
        int count = 0;
        // Gets the two images that are used in the buttons
        final ImageView arrow = createIcon("redArrow.png", theme.textColor);
        final ImageView redArrow = createIcon("redArrow.png", theme.textHoverColor);

        // If this is the last slide then create the restart button
        if (createRestartButton(root, redArrow, arrow)){
            return 1;
        }
        // Creates the other buttons if it is not the last slide
        for (int i = 0; i < player.currentValidDecisions.size(); i++) {
            count++;
            // Creates the button
            Button b = createButton(arrow);
            b.setText(player.currentValidDecisions.get(i).text);
            addDestinationAction(player, b, i);
            addListeners(b, redArrow, arrow);
            root.getChildren().add(b);
        }
        return count;
    }

    /**
     * Creates the restart button when the last slide is reached
     *
     * @return - The number of buttons.
     */
    public boolean createRestartButton(Pane root, ImageView redArrow, ImageView arrow){
        // Checks if there are any more decisions, if not then it creates the replay buttons
        // and returns true
        if (player.currentValidDecisions.size() == 0) {
            Button b = createButton(arrow);
            b.setText("Replay the game?");
            addListeners(b, redArrow, arrow);
            // Handles the action of when the button is pressed
            b.setOnAction(arg0 -> {
                TitleScreen titleScreen = new TitleScreen();
                titleScreen.displayFirstSlide();
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