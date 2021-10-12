package main.java.client;

import main.java.entities.*;
import main.java.interfaces.DisplayGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// GUI class!!!11!11!!!!
public class GameRenderer implements DisplayGame {
    JFrame frame; // Creates JFrame that the gamerenderer will use to display the window

    public GameRenderer() {
        frame = new JFrame("Game");
        frame.setSize(800, 500);
    } //Initializes JFrame

    /**
     * This method displays the Slide slide onto the Jframe
     * @param slide This is the slide to display
     */
    public void display(Slide slide) {
        frame.getContentPane().removeAll(); // Clears the content currently on the jframe
        frame.repaint();
        /* creates a scrolling display that contains the text and buttons for the game */
        JScrollPane scroll = new JScrollPane(createMainPanel(slide));
        frame.add(scroll);
        frame.setVisible(true);
    }

    /**
     * This method creates a panel that contains the text and buttons of the slide of the game
     * @param slide This is the slide that the game is currently on
     * @return JPanel This is the panel that is to be added and displayed on the jframe
     */
    public JPanel createMainPanel(Slide slide) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JTextArea t1;
        t1 = new JTextArea(slide.prompt, 20, 10);
        t1.setLineWrap(true);
        t1.setAlignmentX(Component.LEFT_ALIGNMENT);
        ArrayList<Decision> validDecisions = Player.checkValidChoices(slide.decisions);
        panel.add(t1);
        /*
        Creates the buttons for each decision on the slide
         */
        for (Decision d : validDecisions) {
            JButton b = new JButton(d.text);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Player.currentSlide = d.target;
                    Player.playScene();
                }
            });
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(b);
        }
        return panel;
    }

}