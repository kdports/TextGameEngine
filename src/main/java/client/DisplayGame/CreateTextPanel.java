package client.DisplayGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import entities.Player;
import client.Theme;

public class CreateTextPanel extends JPanel{
    Player player;
    Theme theme;
    int animationSpeed;
    /**
     * This method is the constructor that initiates the instance variables.
     *
     * @param player - The player instance that is currently being used by GameRenderer
     * @param theme - The theme that the GameRenderer is currently using
     */
    CreateTextPanel(Theme theme, Player player, int animationSpeed) {
        // Sets the theme, animation speed and player that is being used by the GameRenderer
        this.theme = theme;
        this.player = player;
        this.animationSpeed = animationSpeed;
    }

    /**
     * This method creates a panel that contains the text and buttons of the slide of the game.
     *
     */
    public void createTPanel(){
        this.setLayout(new BorderLayout());
        // Checks the choices and updates the currentValidDecisions property of player
        player.checkValidChoices();
        JTextArea textArea = createTextArea(this);
        this.setPreferredSize(new Dimension(100, 100));
        this.add(textArea);
    }

    /**
     * The method creates a main text area for the game screen.
     *
     * @param panel - The panel to create the text area in.
     * @return - The text area.
     */
    public JTextArea createTextArea(JPanel panel) {
        JTextArea textArea;
        // Creates a text area with no text and with 20 rows and 10 columns
        textArea = new JTextArea("", 20, 10);
        // Sets the theme, border and other properties of the text area
        textArea.setLineWrap(true);
        textArea.setForeground(theme.textColor);
        textArea.setBackground(theme.backgroundColor);
        textArea.setEditable(false);
        textArea.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        Border border = BorderFactory.createLineBorder(theme.backgroundColor, 10);
        textArea.setBorder(border);
        getFont(textArea);
        addAnimation(textArea);
        return textArea;
    }

    /**
     * The method sets the font for a given JTextArea.
     *
     * @param textArea - The panel to create the text area in.
     */
    public void getFont(JTextArea textArea){
        // Gets the font from the resources folder
        try {
            File font_file = new File("src/main/resources/player/OpenSans-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font sizedFont = font.deriveFont(25f);
            textArea.setFont(sizedFont);
        } catch (FontFormatException | IOException e) {
            // If the font is missing, set it to roboto instead
            textArea.setFont(new Font("roboto", Font.PLAIN, 25));
        }
    }
    /**
     * Animates the text by displaying the slide text as if someone were typing it.
     *
     * @param textArea - The text area to animate.
     */
    public void addAnimation(JTextArea textArea) {
        // Gets the current text
        String text = player.currentSlide.getPrompt();
        // Creates the animation using the animationSpeed
        Timer timer = new Timer(animationSpeed, new ActionListener() {
            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                i = textArea.getText().length();
                if (i >= text.length()) {
                    ((Timer) e.getSource()).stop();
                } else {
                    String textAreaText = textArea.getText();
                    textAreaText += text.charAt(i);
                    textArea.setText(textAreaText);
                }
            }
        });
        // If the user clicks the text, it will skip the animation
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                textArea.setText(text);
            }
        });
        timer.start();
    }
}
