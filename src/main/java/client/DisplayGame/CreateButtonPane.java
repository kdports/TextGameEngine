package client.DisplayGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import entities.Player;
import client.Theme;


public class CreateButtonPane {
    Player player;
    Theme theme;

    /**
     * This is the constructor method that initiates the instance variables
     *
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme - The theme that the GameRenderer is currently using
     */
    CreateButtonPane(Player player, Theme theme){
        // Sets the theme and the player classes
        this.theme = theme;
        this.player = player;
        // Sets the background

    }

    /**
     * Creates the pane with all the buttons to add to the screen.
     *
     */
    public Pane createBPane(){
        VBox bPane = new VBox();
        bPane.setStyle("-fx-background-color: black;"); // TODO: Use the theme colors somehow
        bPane.setStyle("-fx-border-width: 0px;");
        CreateButtons buttons = new CreateButtons(player, theme);
        int num = buttons.createButtons(bPane);
        // Sets the size of the window and the layout
        bPane.setPrefSize(100, num * 25);
        return bPane;
    }





    /**
     * Adds listeners to the button choices so that they change color when hovered.
     *
     * @param button - Button to add listeners to.
     * @param hoverArrow - The image of the hovered arrow.
     * @param arrow - The ImageIcon of the normal arrow.
     */
    public void addListeners(Button button, ImageView hoverArrow, ImageView arrow) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> {
                    button.setTextFill(theme.textHoverColor);
                    button.setGraphic(hoverArrow);
                });

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> {
                    button.setTextFill(theme.textColor);
                    button.setGraphic(arrow);
                });
    }

//    /**
//     * Creates ImageIcon from the filename and recolors the image to a certain color.
//     *
//     * @param filename - Filename to load the icon image from.
//     * @param color - Color to color the image.
//     * @return - The image.
//     */
//    public ImageView createIcon(String filename, Color color) {
//        Image image  = new Image("src/main/resources/player/" + filename);
//        ImageView white = new ImageView(image);
//        Image newImg = white.getScaledInstance(25, 20, java.awt.Image.SCALE_SMOOTH);
//        ImageView white1 = new ImageView(newImg);
//
//        BufferedImage img = ChangeImage.intoBuffer(white1);
//        return ChangeImage.colorImage(img, color);
//    }

    // Temp function
    public ImageView createIcon(String filename, Color color) {
        Image image  = new Image("file:src/main/resources/player/" + filename, 25, 20, false, true);
        return new ImageView(image);
    }


}
