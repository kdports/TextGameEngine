package client.DisplayGame;

import client.EditorTheme;
import client.ThemeColours;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class SettingsMenu {
    TitleScreen titleScreen;
    ThemeColours theme;
    Stage stage;
    /**
     * Creates the menu bar at the top of the window.
     *
     */
    SettingsMenu(ThemeColours theme, TitleScreen titleScreen) {
        this.theme = theme;
        this.titleScreen = titleScreen;
        this.stage = new Stage();
    }

    public void initializeSettings(){
        VBox root = new VBox(40);
        Scene scene = new Scene(root);

        // Sets the theme to the current theme
        root.setStyle("-fx-background-color: " + theme.active.backgroundColour);

        VBox animation = createAnimationBox();
        VBox themes = createThemeBox();
        root.getChildren().addAll(themes, animation);

        // Sets the size for the root
        root.setPrefSize(400, 640);
        stage.setTitle("Settings");
        stage.setScene(scene);
    }

    public void displaySettings(){
        stage.show();
    }

    public void reDisplaySettings(){
        stage.close();
        initializeSettings();
        stage.show();
    }


    /**
     * Creates the animation menu for the menu bar.
     *
     * @return - The animation menu;
     */
    public VBox createAnimationBox() {
        // Crates an animation speed dropdown to allow users to switch the animation speed
        // Speed contains the 4 options for the animation speed
        String[] speed = {"Off", "Fast", "Medium", "Slow"};
        VBox animation = new VBox();
        animation.setAlignment(Pos.CENTER);
        addLabel("Animation Speed", animation);
        List<Button> buttons = new ArrayList<>();

        int speed_num = 0;
        for (String s : speed) {
            Button button = createSettingsButton(s);
            buttons.add(button);
            int finalSpeed_num = speed_num;
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> titleScreen.gameRenderer.animationSpeed = finalSpeed_num);
            // Increases the speed with each option
            speed_num += 5;
            speed_num *= 2;
            animation.getChildren().add(button);
        }
        updateAnimationButtons(buttons);
        addListeners(buttons);
        return animation;
    }

    /**
     * Creates the animation menu for the menu bar.
     *
     * @return - The animation menu;
     */
    public VBox createThemeBox() {
        VBox themes = new VBox();
        themes.setAlignment(Pos.CENTER);
        List<Button> buttons = new ArrayList<>();
        addLabel("Themes", themes);
        // Creates a theme button for each theme
        for (EditorTheme t : theme.themes) {
            Button button = createSettingsButton(t.name);
            buttons.add(button);
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                theme.active = t;
                titleScreen.reDisplay();
                reDisplaySettings();
            });
            themes.getChildren().add(button);
        }
        updateThemeButtons(buttons);
        addListeners(buttons);
        return themes;
    }
    public void addLabel(String text, VBox box){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color:" + theme.active.backgroundColour + "; -fx-font-size: 25px;" +
                "-fx-text-fill: " + theme.active.textColour + ";-fx-font-weight: 900");
        box.getChildren().add(label);
    }

    public void updateThemeButtons(List<Button> buttons) {
        for (int i = 0; i < theme.themes.size(); i++){
            if (theme.active == theme.themes.get(i)){
                for (Button button: buttons){
                    button.setDisable(false);
                }
                buttons.get(i).setDisable(true);
            }
        }
    }

    public void updateAnimationButtons(List<Button> buttons) {
        int speed = 0;
        for (int i = 0; i < 4; i++){
            if (titleScreen.gameRenderer.animationSpeed == speed){
                for (Button button: buttons){
                    button.setDisable(false);
                }
                buttons.get(i).setDisable(true);
            }
            speed = (speed + 5)*2;
        }
    }

    public void addListeners(List<Button> buttons) {
        for (Button button : buttons) {
            button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> button.setStyle("-fx-background-color:" + theme.active.textColour + "; -fx-border-width: 0px;" +
                            "-fx-text-fill: " + theme.active.slideColour));

            button.addEventHandler(MouseEvent.MOUSE_EXITED,
                    e -> button.setStyle("-fx-background-color:" + theme.active.slideColour + "; -fx-border-width: 0px;" +
                            "-fx-text-fill: " + theme.active.textColour));
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                for (Button b : buttons) {
                    b.setDisable(false);
                }
                button.setDisable(true);

            });
        }
    }

    public Button createSettingsButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 22));
        button.setPrefSize(200, 75);
        button.setCursor(Cursor.HAND);
        button.setStyle("-fx-background-color: " + theme.active.slideColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        return button;
    }
}