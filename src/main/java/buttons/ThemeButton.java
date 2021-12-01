package buttons;

import client.EditorTheme;
import client.ThemeColours;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The button that allows the user to create a new slide.
 */
public class ThemeButton extends MenuButton {

    public ThemeButton(ThemeColours theme, ScrollPane scrollPane) {
        super(scrollPane);
        this.setLayoutY(425);

        this.setText("Change Theme");

        // set the theme to the current active theme
        this.setStyle("-fx-background-color: " + theme.active.sidebarColour + ";" +
                "-fx-text-fill: " + theme.active.textColour);

        this.setOnMouseClicked(event -> {
            Stage themeWindow = new Stage();
            themeWindow.initModality(Modality.APPLICATION_MODAL);
            themeWindow.setTitle("Theme Editor");
            themeWindow.setMinWidth(300);

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
                    }
                }
            });



            // Make the Slide Edit Window Show
            VBox alert = new VBox();
            alert.getChildren().addAll(choiceBox);
            alert.setAlignment(Pos.CENTER);

            Scene window = new Scene(alert);
            themeWindow.setScene(window);
            themeWindow.show();
        });
    }
}
