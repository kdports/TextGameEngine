package client.GameRenderer;


import client.Theme;
import entities.Player;
import entities.CreateSampleGame;
import interfaces.PlayDisplayer;

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
     * Creates the GameRenderer.
     */
    public GameRenderer() {
        frame = new JFrame("Game");
        // Place holder name for now
        frame.setTitle("Temp Title");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the image in the top left, currently no image
        ImageIcon image = new ImageIcon();
        frame.setIconImage(image.getImage());

        animationSpeed = 30;

        theme = new Theme();
    } //Initializes JFrame

    /**
     * Sets the player for the game.
     *
     * @param player - Player to set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * This method displays each of the slides onto the JFrame.
     */
    public void display() {
        frame.getContentPane().removeAll(); // Clears the content currently on the jframe

        frame.repaint();

        /* creates a scrolling display that contains the text and buttons for the game */
        CreateTextPanel textPanel = new CreateTextPanel(theme, player, animationSpeed);
        JScrollPane textScroll = new JScrollPane(textPanel);
        CreateButtonPanel panel = new CreateButtonPanel(player, theme);
        JScrollPane buttonScroll = new JScrollPane(panel);
        frame.add(textScroll);
        frame.add(buttonScroll, BorderLayout.SOUTH);
        frame.setJMenuBar(createMenu());
        frame.setVisible(true);
    }

    /**
     * Creates the menu bar at the top of the window.
     *
     * @return - The menu bar.
     */
    public JMenuBar createMenu() {
        JMenuBar mb = new JMenuBar();
        // Creates the theme dropdown
        JMenu themes = new JMenu("Themes");
        // Adds the themes from the themeList as a button to allow the user to switch between themes
        for (String t : theme.themeList) {
            JMenuItem th = new JMenuItem(t);
            th.addActionListener(e -> {
                theme.setTheme(t);
                display();
            });
            themes.add(th);
        }
        // Creates the animation dropdown
        JMenu animation = createAnimationMenu();

        // Adds the themes dropdown and the animation dropdown to the menu bar
        mb.add(themes);
        mb.add(animation);
        return mb;
    }

    /**
     * Creates the animation menu for the menu bar.
     *
     * @return - The animation menu;
     */
    public JMenu createAnimationMenu() {
        // Crates an animation speed dropdown to allow users to switch the animation speed
        // Speed contains the 4 options for the animation speed
        String[] speed = {"off", "fast", "medium", "slow"};
        JMenu animation = new JMenu("Animation");
        int speed_num = 0;
        for (String s : speed) {
            JMenuItem sp = new JMenuItem(s);
            int finalSpeed_num = speed_num;
            sp.addActionListener(e -> {
                animationSpeed = finalSpeed_num;
                display();
            });
            // Increases the speed with each option
            speed_num += 5;
            speed_num *= 2;
            animation.add(sp);
        }
        return animation;
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