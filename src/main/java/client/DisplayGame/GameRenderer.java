package client.DisplayGame;


import client.Theme;
import entities.Player;
import entities.CreateSampleGame;
import client.PlayDisplayer;

import javax.swing.*;
import java.awt.*;

/**
 * The class that is in charge of rendering the game.
 */
public class GameRenderer implements PlayDisplayer {
    JFrame frame; // Creates JFrame that the GameRenderer will use to display the window
    Player player;// The player that plays the game to render
    Theme theme;

    int animationSpeed;

    /**
     * The simple constructor for the GameRenderer that creates a JFrame to display the game
     */
    public GameRenderer() {
        frame = new JFrame("Game");
        // Place holder name for now
        frame.setTitle("Temp Title");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Sets the image in the top left, currently no image
        ImageIcon image = new ImageIcon();
        frame.setIconImage(image.getImage());

        animationSpeed = 30;

        theme = new Theme();
    }

    /**
     * This method displays each of the slides onto the JFrame.
     */
    public void display() {
        frame.getContentPane().removeAll(); // Clears the content currently on the jframe

        frame.repaint();

        /* creates a scrolling display that contains the text and buttons for the game */
        CreateMenu menu = new CreateMenu(theme, player, animationSpeed, frame, this);
        frame.setJMenuBar(menu.createMenu());
        CreateTextPanel textPanel = new CreateTextPanel(theme, player, animationSpeed);
        textPanel.createTPanel();
        JScrollPane textScroll = new JScrollPane(textPanel);
        CreateButtonPanel panel = new CreateButtonPanel(player, theme);
        panel.createBPanel();
        JScrollPane buttonScroll = new JScrollPane(panel);
        frame.add(textScroll);
        frame.add(buttonScroll, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Sets the player for the game.
     *
     * @param player - Player to set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, CreateSampleGame.returnGame());
        p.playGame();
    }

}