package client.DisplayGame;

import client.ThemeColours;
import entities.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Object that creates the Text Pane for the Game Renderer
 */
public class CreateTextPane {
    int animationSpeed;
    ThemeColours theme;
    Player player;

    /**
     * The method creates a main text area for the game screen.
     *
     * @param player         - The player of the game
     * @param theme          -  The color theme for the panel
     * @param animationSpeed -  The speed to write the text on the screen
     */
    CreateTextPane(Player player, ThemeColours theme, int animationSpeed) {
        this.player = player;
        this.theme = theme;
        this.animationSpeed = animationSpeed;
    }

    /**
     * This method creates a panel that contains the text and buttons of the slide of the game.
     *
     * @return - The text pane that was created.
     */
    public Pane createTPane() {
        VBox box = new VBox();
        // Checks the choices and updates the currentValidDecisions property of player
        player.checkValidChoices();
        // Creates the label with the text to put on the pane
        Label label = createText();
        box.setPrefSize(100, 100);
        box.getChildren().add(label);
        box.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        addAnimation(label, box);
        return box;
    }

    /**
     * This method creates a label filled with the current slide's text.
     *
     * @return - The label that contains the text.
     */
    public Label createText() {
        Label label = new Label();
        // Adds padding so that the text is not right next to the border
        label.setPadding(new Insets(15, 20, 15, 20));
        label.setText(player.currentSlide.getPrompt());
        label.setTextFill(Color.web(theme.active.textColour));
        VBox.setVgrow(label, Priority.ALWAYS);
        getFont(label);
        label.setWrapText(true);
        return label;
    }


    /**
     * The method sets the font for a given Label.
     *
     * @param text - The panel to create the text area in.
     */
    public void getFont(Label text) {
        // Gets the font from the resources folder
        text.setFont(Font.loadFont("file:resources/player/OpenSans-Regular.ttf", 28));
        text.setStyle("-fx-font-size: 28px");
    }


    /**
     * Animates the text by displaying the slide text as if someone were typing it.
     *
     * @param text - The text area to animate.
     * @param box  - The pane that the text will be on
     */
    public void addAnimation(Label text, Pane box) {
        // Gets the current text
        String slideText = player.currentSlide.getPrompt();
        // Counts the number of letters that are displayed
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        if (animationSpeed == 0) {
            text.setText(slideText);
        } else {
            KeyFrame keyFrame = new KeyFrame(
                    // Pauses for an amount of time based on the animationspeed
                    Duration.millis(animationSpeed),
                    event -> {
                        // Stops the animation when we run out of text
                        if (i.get() > slideText.length()) {
                            timeline.stop();
                        } else {
                            text.setText(slideText.substring(0, i.get()));
                            i.set(i.get() + 1);
                        }
                    });
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
        // If the user clicks the text, it will skip the animation
        box.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            text.setText(slideText);
            timeline.stop();
        });
    }
}