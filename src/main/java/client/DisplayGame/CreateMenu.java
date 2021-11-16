package client.DisplayGame;

import client.Theme;
import entities.Player;

import javax.swing.*;

/**
 * This class is in charge of creating the top menu bar of the game player that is in charge
 * of things like changing themes and animation speed
 */
public class CreateMenu extends GameRenderer {
    GameRenderer gameRenderer;

    /**
     * Constructor that creates a createmenu object
     * @param theme A theme object with colors of various elements
     * @param player The player object that gamerenderer uses
     * @param animationSpeed The speed of the text loading animation
     * @param frame The JFrame that Gamerenderer uses to display the game
     * @param gameRenderer The Gamerenderer that displays the game
     */
    CreateMenu(Theme theme, Player player, int animationSpeed, JFrame frame, GameRenderer gameRenderer) {
        this.theme = theme;
        this.animationSpeed  = animationSpeed;
        this.player = player;
        this.frame = frame;
        this.gameRenderer = gameRenderer;
    }

    /**
     * Creates a JMenuBar that resides at the top of the game renderer which gives you various options
     * to customize the game
     * @return JMenuBar Returns the JMenuBar created
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
                gameRenderer.display();
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
     * Creates the animation menu for the menu bar that alters the speed of
     * the text displaying
     *
     * @return - The animation menu
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
                gameRenderer.animationSpeed = finalSpeed_num;
                gameRenderer.display();
            });
            // Increases the speed with each option
            speed_num += 5;
            speed_num *= 2;
            animation.add(sp);
        }
        return animation;
    }
}
