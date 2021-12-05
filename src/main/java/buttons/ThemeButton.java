package buttons;

import client.EditorTheme;
import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import client.ThemeColours;
import entities.EditorGame;
import entities.Slide;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Objects;

import static java.lang.Math.abs;

/**
 * The button that allows the user to create a new slide.
 */
public class ThemeButton extends MenuButton {

    public ThemeButton(ThemeColours theme, ScrollPane scrollPane, Pane root) {
        super(scrollPane);
        this.setLayoutY(425);
        scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> this.setLayoutY(abs(newvalue.getMinY()) + 425)
        );
        scrollPane.vvalueProperty().addListener((observable, oldvalue, newvalue) -> {
                    this.setLayoutY(newvalue.doubleValue() + 425);
                }
        );

        this.setText("Change Theme");
        this.setId("button-change-theme");
        // set the theme to the current active theme
        this.setStyle("-fx-background-color: " + theme.active.sidebarColour + ";" +
                "-fx-text-fill: " + theme.active.textColour);

        this.setOnMouseClicked(event -> {
            Stage themeWindow = new Stage();
            themeWindow.setX(60);
            themeWindow.setY(430);
            themeWindow.initModality(Modality.APPLICATION_MODAL);
            themeWindow.setTitle("Theme Editor");
            themeWindow.setMinWidth(140);

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.setValue(theme.active.name);
            for (EditorTheme themes : theme.themes){
                choiceBox.getItems().add(themes.name);
            }

            // When u click a theme name, change the active theme to that theme
            choiceBox.setOnAction(event1 -> {
                String newTheme = choiceBox.getValue();
                for(EditorTheme themeSet : theme.themes){
                    if (Objects.equals(themeSet.name, newTheme)){
                        theme.active = themeSet;
                        ChangeTheme(root, theme.active, theme);
                    }
                }
                themeWindow.close();
            });



            // Make the Slide Edit Window Show
            VBox alert = new VBox();
            alert.getChildren().addAll(choiceBox);
            alert.setAlignment(Pos.CENTER);
            choiceBox.setId("theme-picker");

            Scene window = new Scene(alert);
            themeWindow.setScene(window);
            themeWindow.show();
            themeWindow.setFullScreen(false);
        });
    }

    public void ChangeTheme(Pane root, EditorTheme active, ThemeColours theme){
        for (Node element : root.getChildren()){

            if (element instanceof GuiSlide){
                ((GuiSlide) element).setTheme(theme);
            }
            else if (element instanceof GuiDecision){
                ((GuiDecision) element).setTheme(theme);
            }
            else if (element instanceof StackPane){
                element.setStyle("-fx-background-color: " + active.backgroundColour);
            }
            else if (element instanceof Button){
                element.setStyle("-fx-background-color: " + theme.active.sidebarColour + ";" +
                        "-fx-text-fill: " + theme.active.textColour);
            }

        }
    }
}
