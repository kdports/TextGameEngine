package client.DisplayGame;

import client.EditorTheme;
import client.ThemeColours;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the Settings Menu for the main title screen of the Game Player
 */
public class SettingsMenu {
    TitleScreen titleScreen;
    ThemeColours theme;
    Stage stage;

    /**
     * Creates the menu bar at the top of the window.
     *
     * @param theme       - The theme class that is being used.
     * @param titleScreen - The titlescreen that the settings are being displayed for.
     */
    SettingsMenu(ThemeColours theme, TitleScreen titleScreen) {
        this.theme = theme;
        this.titleScreen = titleScreen;
        this.stage = new Stage();
    }

    /**
     * Initializes the settings so that it is ready to display
     */
    public void initializeSettings() {
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

    /**
     * Displays the settings when the button is clicked
     */
    public void displaySettings() {
        stage.show();
    }

    /**
     * Redisplays the settings when the theme is updated.
     */
    public void reDisplaySettings() {
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
            Button button = createMenuButton(s);
            buttons.add(button);
            int finalSpeed_num = speed_num;
            // Whenever the button is pressed, it changes the animationspeed
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
        // Keeps a list of all the theme buttons
        List<Button> buttons = new ArrayList<>();
        addLabel("Themes", themes);
        // Creates a theme button for each theme
        for (EditorTheme t : theme.themes) {
            Button button = createMenuButton(t.name);
            buttons.add(button);
            // Whenever the button is pressed, activates that theme and redisplays the game player
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

    /**
     * Adds a basic label that contains the text that was passed in.
     *
     * @param text -  the text for the label
     * @param pane - the pane to add the label to.
     */
    public void addLabel(String text, Pane pane) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color:" + theme.active.backgroundColour + "; -fx-font-size: 25px;" +
                "-fx-text-fill: " + theme.active.textColour + ";-fx-font-weight: 900");
        pane.getChildren().add(label);
    }

    /**
     * Updates the theme buttons so that the current theme's button is disabled.
     *
     * @param buttons -  the list of all the theme buttons
     */
    public void updateThemeButtons(List<Button> buttons) {
        for (int i = 0; i < theme.themes.size(); i++) {
            // Undisables the theme buttons and disables the one that was pressed
            if (theme.active == theme.themes.get(i)) {
                for (Button button : buttons) {
                    button.setDisable(false);
                }
                buttons.get(i).setDisable(true);
            }
        }
    }

    /**
     * Updates the animation buttons so that the current animation's button is disabled.
     *
     * @param buttons -  the list of all the animation buttons
     */
    public void updateAnimationButtons(List<Button> buttons) {
        int speed = 0;
        for (int i = 0; i < 4; i++) {
            if (titleScreen.gameRenderer.animationSpeed == speed) {
                // Undisables the animation buttons and disables the one that was pressed
                for (Button button : buttons) {
                    button.setDisable(false);
                }
                buttons.get(i).setDisable(true);
            }
            speed = (speed + 5) * 2;
        }
    }

    /**
     * Adds the listeners to the buttons so that it has an effect when they are hovered over.
     *
     * @param buttons -  the list of all the buttons
     */
    public void addListeners(List<Button> buttons) {
        for (Button button : buttons) {
            // Adds the hover effects for the buttons
            button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    e -> button.setStyle("-fx-background-color:" + theme.active.textColour + "; -fx-border-width: 0px;" +
                            "-fx-text-fill: " + theme.active.slideColour));

            button.addEventHandler(MouseEvent.MOUSE_EXITED,
                    e -> button.setStyle("-fx-background-color:" + theme.active.slideColour + "; -fx-border-width: 0px;" +
                            "-fx-text-fill: " + theme.active.textColour));
            // Disables the button when it is pressed and undisables the other buttons
            button.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
                for (Button b : buttons) {
                    b.setDisable(false);
                }
                button.setDisable(true);
            });
        }
    }

    /**
     * Creates a button for the settings menu
     *
     * @param text -  The text to add to the button
     * @return - The menu button
     */
    public Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 22));
        button.setPrefSize(200, 75);
        button.setCursor(Cursor.HAND);
        button.setStyle("-fx-background-color: " + theme.active.slideColour + ";" +
                "-fx-text-fill: " + theme.active.textColour + ";");
        return button;
    }
}