package client.DisplayGame;

import client.ThemeColours;
import entities.Player;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import static java.lang.Long.MAX_VALUE;

/**
 * Creates the buttons for the create button panel
 */
public class CreateButtons extends CreateButtonPane{

    /**
     * Creates the panel with all the buttons to add to the screen.
     *  @param player - The player instance that is currently being used by GameRenderer
     * @param theme  - The theme that the GameRenderer is currently using
     */
    CreateButtons(Player player, ThemeColours theme) {
        super(player, theme);
    }

    /**
     * Creates 1 button that is styled as one of the decisions for a slide.
     *
     * @param arrow - The image to appear at the start of the button
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
        b.setStyle("-fx-background-color:" + theme.active.backgroundColour + "; -fx-border-width: 0px;" +
                "-fx-text-fill: " + theme.active.textColour);

        b.setCursor(Cursor.HAND);
        return b;
    }

    /**
     * Creates buttons for all the decisions in a slide.
     *
     * @param root - The panel to add the buttons to
     * @return - The number of buttons.
     */
    public int createButtons(Pane root) {
        // Keeps count of how many buttons are in each Slide
        int count = 0;
        // Gets the two images that are used in the buttons
        final ImageView arrow = createIcon("redArrow.png", theme.active.textColour);
        final ImageView redArrow = createIcon("redArrow.png", theme.active.slideColour);

        // If this is the last slide then create the restart button
        if (createRestartButton(root, redArrow, arrow)){
            return 1;
        }
        createInventorButton(root);
        count +=1 ;
        // Creates the other buttons if it is not the last slide
        for (int i = 0; i < player.currentValidDecisions.size(); i++) {
            count++;
            // Creates the button
            Button b = createButton(createIcon("redArrow.png", theme.active.textColour));
            b.setText(player.currentValidDecisions.get(i).text);
            addDestinationAction(player, b, i);
            addListeners(b, createIcon("redArrow.png", theme.active.slideColour), createIcon("redArrow.png", theme.active.textColour));
            root.getChildren().add(b);
        }
        return count;
    }

    /**
     * Creates the restart button when the last slide is reached
     *
     * @return - Whether or not it was created.
     */
    public boolean createRestartButton(Pane root, ImageView hoverArrow, ImageView arrow){
        // Checks if there are any more decisions, if not then it creates the replay buttons
        // and returns true
        if (player.currentValidDecisions.size() == 0) {
            Button b = createButton(arrow);
            b.setText("Replay the game?");
            addListeners(b, hoverArrow, arrow);
            // Handles the action of when the button is pressed
            b.setOnAction(arg0 -> {
                player.clearPastChosenDecisions();
                player.clearInventory();
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
            if (player.currentValidDecisions.get(index).hasItemToGive()) {
                player.receiveItem(player.currentValidDecisions.get(index));
            }
            player.AddToPastChosenDecisions(player.currentValidDecisions.get(index));
            player.playScene();
        });
    }

    /**
     * Creates the inventory button that shows the current inventory when pressed
     *
     * @return - The number of buttons.
     */
    private void createInventorButton(Pane root){
        final ImageView backpack = createIcon("backpack.png", theme.active.textColour);
        final ImageView hoverBackpack = createIcon("redArrow.png", theme.active.slideColour);
        Button b = createButton(backpack);
        b.setText("Inventory");
        addListeners(b, backpack, hoverBackpack);

        b.setOnAction(arg0 -> {
            this.displayInventory();
        });

        root.getChildren().add(b);

    }

    /**
     * Creates the inventory button that shows the current inventory when pressed
     *
     * @return - The number of buttons.
     */
    private void displayInventory(){
        VBox root = new VBox();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        player.addToItems("pie");
        player.addToItems("pi");
        player.addToItems("pie4");
        player.addToItems("pie3");
        player.addToItems("pie2");
        player.addToItems("pie45");
        player.addToItems("pie66");
        player.addToItems("pie6666");
        player.addToItems("pi44e");
        player.addToItems("pi44e");


        int count = 0;
        root.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        for (String item: player.getInventory()){
            Label text = new Label(" " + item);
            final ImageView arrow = createIcon("itemArrow.png", theme.active.textColour);
            text.setGraphic(arrow);
            text.setStyle("-fx-background-color:" + theme.active.backgroundColour + "; -fx-border-width: 0px;" +
                    "-fx-text-fill: " + theme.active.textColour);
            text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR, 25));
            root.getChildren().add(text);
            count += 1;
        }
        root.setPrefSize(300, count * 25);
        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();
    }
}
