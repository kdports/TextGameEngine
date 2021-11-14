package client.GameRenderer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import entities.Player;
import client.Theme;

public class CreateButtonPanel extends JPanel{
    Player player;
    Theme theme;

    /**
     * Creates the panel with all the buttons to add to the screen.
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme - The theme that the GameRenderer is currently using
     */
    CreateButtonPanel(Player player, Theme theme){
        // Sets the theme and the player classes
        this.theme = theme;
        this.player = player;
        // Sets the background
        this.setBackground(theme.backgroundColor);
        int num = createButtons(this);
        // Sets the size of the window and the layout
        this.setLayout(new GridLayout(num, 1));
        this.setPreferredSize(new Dimension(100, num * 50));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    /**
     * Creates buttons for all the decisions in a slide.
     * @param panel - The panel to add the buttons to
     * @return - The number of buttons.
     */
    public int createButtons(JPanel panel) {
        // Keeps count of how many buttons are in each Slide
        int count = 0;
        // Gets the two images that are used in the buttons
        final ImageIcon arrow = createIcon("redArrow.png", theme.textColor);
        final ImageIcon redArrow = createIcon("redArrow.png", theme.textHoverColor);

        // If this is the last slide then create the restart button
        if (createRestartButton(arrow, redArrow, panel)){
            return 1;
        }
        // Creates the other buttons if it is not the last slide
        for (int i = 0; i < player.currentValidDecisions.size(); i++) {
            count++;

            JButton b = createButton(arrow);
            b.setText(player.currentValidDecisions.get(i).text);
            addDestinationAction(b, i);
            addListeners(b, redArrow, arrow);
            panel.add(b);
        }
        return count;
    }

    /**
     * Creates the restart button when the last slide is reached
     * @param arrow - The image for the white arrow in the button
     * @param redArrow - The image for the red arrow in the button
     * @param panel - The panel to add the buttons to
     * @return - The number of buttons.
     */
    public boolean createRestartButton(ImageIcon arrow, ImageIcon redArrow, JPanel panel){
        // Checks if there are any more decisions, if not then it creates the replay buttons
        // and returns true
        if (player.currentValidDecisions.size() == 0) {
            JButton b = createButton(arrow);
            b.setText("Replay the game?");
            addListeners(b, redArrow, arrow);
            b.addActionListener(e -> {
                player.currentSlide = player.game.firstSlide;
                player.playScene();
            });
            panel.add(b);
            return true;
        }
        return false;
    }

    /**
     * Adds listeners to the button choices so that they change color when hovered.
     *
     * @param b - Button to add listeners to.
     * @param hoverArrow - The image of the hovered arrow.
     * @param arrow - The ImageIcon of the normal arrow.
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
     * Creates 1 button that is styled as one of the decisions for a slide.
     *
     * @param arrow - The image to appear at the start of the button
     * @return - The button.
     */
    private JButton createButton(ImageIcon arrow) {
        // Creates a button and sets the theme, font, image, etc of the button.
        JButton b = new JButton();
        b.setIcon(arrow);
        b.setFocusable(false);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setFont(new Font("Rockwell", Font.PLAIN, 25));
        b.setBackground(theme.backgroundColor);
        b.setForeground(theme.textColor);
        b.setBorder(null);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return b;
    }
    /**
     * Creates ImageIcon from the filename and recolors the image to a certain color.
     *
     * @param filename - Filename to load the icon image from.
     * @param color - Color to color the image.
     * @return - The image.
     */
    public ImageIcon createIcon(String filename, Color color) {
        ImageIcon white = new ImageIcon("src/main/resources/player/" + filename);
        Image image = white.getImage();
        Image newImg = image.getScaledInstance(25, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon white1 = new ImageIcon(newImg);
        BufferedImage img = intoBuffer(white1);
        return colorImage(img, color);
    }


    /**
     * Adds which slide to go to when the button is clicked.
     *
     * @param button - The button that will be clicked.
     * @param index - The index of the decision linked to the button.
     */
    private void addDestinationAction(JButton button, int index) {
        button.addActionListener(e -> {
            player.currentSlide = player.currentValidDecisions.get(index).target;
            player.playScene();
        });
    }

    /**
     * Turns an image icon into a buffered image.
     *
     * @param icon - The image icon to transform.
     * @return - The image icon as a bufferedImage.
     */
    public BufferedImage intoBuffer(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();

        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }

    /**
     * Changes all the colored pixels in an BufferedImage to another color.
     *
     * @param image - The image to recolor.
     * @param color - The color to recolor.
     * @return - The recolored image.
     */
    private static ImageIcon colorImage(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        // Changes the color of the image to the specified color
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int[] pixels = raster.getPixel(x, y, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(x, y, pixels);
            }
        }
        return new ImageIcon(image);
    }

}
