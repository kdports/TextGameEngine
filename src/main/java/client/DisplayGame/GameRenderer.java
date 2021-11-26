package client.DisplayGame;


import client.Theme;
import entities.Player;
import entities.CreateSampleGame;
import interfaces.PlayDisplayer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * The class that is in charge of rendering the game.
 */
public class GameRenderer extends Application implements PlayDisplayer {
    VBox root; // Creates JFrame that the GameRenderer will use to display the window
    Theme theme;
    private static Player player;
    private static Stage stage;


    int animationSpeed;

    /**
     * Creates the GameRenderer.
     */
    public GameRenderer() {
        root = new VBox();

        animationSpeed = 30;

        theme = new Theme();
    } //Initializes JFrame

    public void begin(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("First JavaFX Application");
        player.playGame();
    }

    /**
     * This method displays each of the slides onto the JFrame.
     */
    public void display() {
        root = new VBox();


        /* creates a scrolling display that contains the text and buttons for the game */
//        CreateMenu menu = new CreateMenu(theme, player, animationSpeed, frame, this);
//        frame.setJMenuBar(menu.createMenu());
//        CreateTextPanel textPanel = new CreateTextPanel(theme, player, animationSpeed);
//        textPanel.createTPanel();
//        JScrollPane textScroll = new JScrollPane(textPanel);
//        CreateButtonPanel panel = new CreateButtonPanel(player, theme);
//        panel.createBPanel();
//        JScrollPane buttonScroll = new JScrollPane(panel);
//        frame.add(textScroll);
//        frame.add(buttonScroll, BorderLayout.SOUTH);
//
//        frame.setVisible(true);
        root.getChildren().add(CreateTextPanel.createTextArea(player));
        CreateButtons.createButtons(player, root);
        Scene scene=new Scene(root,1200,800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the game player
     * @param player The player to be used as the game player
     */
    public void setPlayer(Player player){
        GameRenderer.player = player;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        ggame gr = new ggame();
        new Player(gr, CreateSampleGame.returnGame());
        gr.begin(args);
    }

}