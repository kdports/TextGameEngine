package client.DisplayGame;

import client.Theme;
import client.ThemeColours;
import entities.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Creates the buttons for the create button panel
 */
public class CreateButtons extends CreateButtonPanel{

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
    private JButton createButton(ImageIcon arrow) {
        // Creates a button and sets the theme, font, image, etc. of the button.
        JButton b = new JButton();
        b.setIcon(arrow);
        b.setFocusable(false);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setFont(new Font("Rockwell", Font.PLAIN, 25));
        b.setBackground(Color.decode(theme.active.backgroundColour));
        b.setForeground(Color.decode(theme.active.textColour));
        b.setBorder(null);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return b;
    }

    /**
     * Creates buttons for all the decisions in a slide.
     *
     * @param panel - The panel to add the buttons to
     * @return - The number of buttons.
     */
    public int createButtons(JPanel panel) {
        // Keeps count of how many buttons are in each Slide
        int count = 0;
        // Gets the two images that are used in the buttons
        final ImageIcon arrow = createIcon("redArrow.png", Color.decode(theme.active.textColour));
        final ImageIcon redArrow = createIcon("redArrow.png", Color.decode(theme.active.sidebarColour));

        // If this is the last slide then create the restart button
        if (createRestartButton(arrow, redArrow, panel)){
            return 1;
        }
        // Creates the other buttons if it is not the last slide by looping through the slide
        //decisions
        for (int i = 0; i < player.currentValidDecisions.size(); i++) {
            count++;
            // creates the button
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
     *
     * @param arrow - The image for the white arrow in the button
     * @param redArrow - The image for the red arrow in the button
     * @param panel - The panel to add the buttons to
     * @return boolean - Returns true if button is created
     */
    public boolean createRestartButton(ImageIcon arrow, ImageIcon redArrow, JPanel panel){
        // Checks if there are any more decisions, if not then it creates the replay buttons
        // and returns true
        if (player.currentValidDecisions.size() == 0) {
            JButton b = createButton(arrow);
            b.setText("Replay the game?");
            addListeners(b, redArrow, arrow);
            //Handles the action of when the button gets pressed
            b.addActionListener(e -> {
                player.clearPastChosenDecisions();
                player.clearInventory();
                player.currentSlide = player.game.firstSlide;
                player.playScene();
            });
            panel.add(b);
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
    private void addDestinationAction(JButton button, int index) {
        button.addActionListener(e -> {
            player.currentSlide = player.currentValidDecisions.get(index).target;
            if (player.currentValidDecisions.get(index).hasItemToGive()) {
                player.receiveItem(player.currentValidDecisions.get(index));
            }
            player.AddToPastChosenDecisions(player.currentValidDecisions.get(index));
            player.playScene();
        });
    }
}
