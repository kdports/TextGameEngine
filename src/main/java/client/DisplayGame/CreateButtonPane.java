package client.DisplayGame;

import client.ThemeColours;
import entities.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This class creates the Button Pane for the Game Renderer
 */
public class CreateButtonPane {
    Player player;
    ThemeColours theme;

    /**
     * This is the constructor method that initiates the instance variables
     *
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme  - The theme that the GameRenderer is currently using
     */
    CreateButtonPane(Player player, ThemeColours theme) {
        // Sets the theme and the player classes
        this.theme = theme;
        this.player = player;
        // Sets the background

    }

    /**
     * Creates the pane with all the buttons to add to the screen.
     *
     * @return - the pane for the buttons that was created
     */
    public Pane createBPane() {
        VBox bPane = new VBox();
        // Sets the background to match the current theme
        bPane.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        // Creates a padding so that the buttons are not right next to the border
        bPane.setPadding(new Insets(6, 2, 6, 2));
        CreateButtons buttons = new CreateButtons(player, theme);
        // Counts the number of buttons so that we can set the right size for the pane
        int num = buttons.createButtons(bPane);
        // Sets the size of the window and the layout
        bPane.setPrefSize(100, num * 25);
        return bPane;
    }


    /**
     * Adds listeners to the button choices so that they change color when hovered.
     *
     * @param button     - Button to add listeners to.
     * @param hoverArrow - The image of the hovered arrow.
     * @param arrow      - The ImageIcon of the normal arrow.
     */
    public void addListeners(Button button, ImageView hoverArrow, ImageView arrow) {
        // Changes the button colour when a mouse hovers over it
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    button.setStyle("-fx-background-color:" + theme.active.backgroundColour + "; -fx-border-width: 0px;" +
                            "-fx-text-fill: " + theme.active.slideColour);
                    button.setGraphic(hoverArrow);
                });
        // Changes the colour back to the original when the mouse leaves the button
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    button.setStyle("-fx-background-color:" + theme.active.backgroundColour + "; -fx-border-width: 0px;" +
                            "-fx-text-fill: " + theme.active.textColour);
                    button.setGraphic(arrow);
                });
    }


    /**
     * Creates ImageIcon from the filename and recolors the image to a certain color.
     *
     * @param filename - Filename to load the icon image from.
     * @param string   - Color to color the image.
     * @return - The colored image.
     */
    public ImageView createIcon(String filename, String string) {
        Image image = new Image("file:src/main/resources/player/" + filename, 24, 24, false, true);
        Lighting lighting = new Lighting(new Light.Distant(45, 90, Color.web(string)));
        ColorAdjust bright = new ColorAdjust(0, 1, 1, 1);
        lighting.setContentInput(bright);
        lighting.setSurfaceScale(0.0);
        ImageView imageView = new ImageView(image);
        imageView.setEffect(lighting);
        return imageView;
    }


}
