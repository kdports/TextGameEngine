package client.DisplayGame;

import client.Theme;
import client.ThemeColours;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import entities.Player;
import javafx.scene.text.Font;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CreateTextPane {
    int animationSpeed;
    ThemeColours theme;
    Player player;
    /**
     * The method creates a main text area for the game screen.
     *
     * @param player - The player of the game
     * @param theme
     */
    CreateTextPane(Player player, ThemeColours theme, int animationSpeed){
        this.player = player;
        this.theme = theme;
        this.animationSpeed = animationSpeed;

    }

    /**
     * This method creates a panel that contains the text and buttons of the slide of the game.
     */
    public Pane createTPane() {
        VBox box = new VBox();
        // Checks the choices and updates the currentValidDecisions property of player
        player.checkValidChoices();
        Label label = createText();
        box.setPrefSize(100, 100);
        box.getChildren().add(label);
        /* box.setBackground(new Background(
                new BackgroundFill(theme.backgroundColor,
                CornerRadii.EMPTY, Insets.EMPTY))); */
        box.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        addAnimation(label, box);

        return box;
    }



    public Label createText() {
        Label label = new Label();
        label.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD,FontPosture.REGULAR,20));
        label.setText(player.currentSlide.getPrompt());
        label.setTextFill(Color.web(theme.active.textColour));
        VBox.setVgrow(label, Priority.ALWAYS);
        getFont(label);
        label.setWrapText(true);
        return label;
    }



    /**
     * The method sets the font for a given JTextArea.
     *
     * @param text - The panel to create the text area in.
     */
    public void getFont(Label text) {
        // Gets the font from the resources folder
        text.setFont(Font.loadFont("file:resources/player/OpenSans-Regular.ttf", 25));
        text.setStyle("-fx-font-size: 25px");
    }


    /**
     * Animates the text by displaying the slide text as if someone were typing it.
     *
     * @param text - The text area to animate.
     */
    public void addAnimation(Label text, VBox box) {
        // Gets the current text
        String slideText = player.currentSlide.getPrompt();


        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(animationSpeed),
                event -> {
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


        // If the user clicks the text, it will skip the animation
        box.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            text.setText(slideText);
            timeline.stop();

        });
    }

}