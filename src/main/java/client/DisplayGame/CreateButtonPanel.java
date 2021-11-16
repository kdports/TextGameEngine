package client.DisplayGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entities.Player;
import client.Theme;

/**
 * Creates the Button panel that the game player uses to select decisions
 */
public class CreateButtonPanel extends JPanel {
    Player player;
    Theme theme;

    /**
     * This is the constructor method that initiates the instance variables
     *
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme  - The theme that the GameRenderer is currently using
     */
    CreateButtonPanel(Player player, Theme theme) {
        // Sets the theme and the player classes
        this.theme = theme;
        this.player = player;

    }

    /**
     * Creates the panel with all the buttons to add to the screen.
     */
    public void createBPanel() {
        this.setBackground(theme.backgroundColor);
        CreateButtons buttons = new CreateButtons(player, theme);
        int num = buttons.createButtons(this);
        // Sets the size of the window and the layout
        this.setLayout(new GridLayout(num, 1));
        this.setPreferredSize(new Dimension(100, num * 50));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    /**
     * Adds listeners to the button choices so that they change color when hovered.
     *
     * @param b          - Button to add listeners to.
     * @param hoverArrow - The image of the hovered arrow.
     * @param arrow      - The ImageIcon of the normal arrow.
     */
    public void addListeners(JButton b, ImageIcon hoverArrow, ImageIcon arrow) {
        b.addMouseListener(new MouseAdapter() {
            // Sets the listeners for when the mouse enters and exits the button
            public void mouseEntered(MouseEvent e) {
                b.setForeground(theme.textHoverColor);
                b.setIcon(hoverArrow);
            }

            public void mouseExited(MouseEvent e) {
                b.setForeground(theme.textColor);
                b.setIcon(arrow);
            }
        });
    }

    /**
     * Creates ImageIcon from the filename and recolors the image to a certain color.
     *
     * @param filename - Filename to load the icon image from.
     * @param color    - Color to color the image.
     * @return - The recolored loaded ImageIcon
     */
    public ImageIcon createIcon(String filename, Color color) {
        ImageIcon white = new ImageIcon("src/main/resources/player/" + filename);
        Image image = white.getImage();
        Image newImg = image.getScaledInstance(25, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon white1 = new ImageIcon(newImg);
        BufferedImage img = ChangeImage.intoBuffer(white1);
        return ChangeImage.colorImage(img, color);
    }
}
