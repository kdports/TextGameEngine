package main.java.client;

import main.java.entities.*;
import main.java.interfaces.Displayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// GUI class!!!11!11!!!!
public class GameRenderer implements Displayer {
    JFrame frame; // Creates JFrame that the gamerenderer will use to display the window
    Player player;

    public GameRenderer() {
        frame = new JFrame("Game");
        frame.setSize(1200, 800);
    } //Initializes JFrame

    public void setPlayer(Player player){
        this.player = player;
    }

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
                    player.currentSlide = d.target;
                    player.playScene();
                }
            });
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(b);
        }
        return panel;
    }

    public static void main(String[] args) {
        Decision d1 = new Decision(" choice 1");
        ArrayList<Decision> a1 = new ArrayList<>();
        a1.add(d1);
        Decision d12 = new Decision("choice 2 takes you back to choice 1");
        ArrayList<Decision> a2 = new ArrayList<>();
        a2.add(d12);
        Decision d13 = new Decision("choice 3 takes you to end1");
        a2.add(d13);
        Decision d14 = new Decision("choice 4 takes you to end2");
        a2.add(d14);
        Slide s1 = new Slide("Beginning scene", a1);
        Slide s2 = new Slide("this is the second scene, make a choice", a2);
        Slide s3 = new Slide("ending 1");
        Slide s4 = new Slide("ending 2");
        d1.setTarget(s2);
        d12.setTarget(s1);
        d13.setTarget(s3);
        d14.setTarget(s4);

        Game game = new Game(s1);
        game.addSlide(s1);
        game.addSlide(s2);
        game.addSlide(s3);

        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, game);
        p.playGame();
    }

}