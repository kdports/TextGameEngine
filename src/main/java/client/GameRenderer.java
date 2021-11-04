package client;

import entities.Player;
import entities.Decision;
import entities.RDFLoadTest;
import interfaces.Displayer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GameRenderer implements Displayer {
    JFrame frame; // Creates JFrame that the gamerenderer will use to display the window
    Player player;

    public GameRenderer() {
        frame = new JFrame("Game");
        // Place holder name for now
        frame.setTitle("Temp Title");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the image in the top left, currently no image
        ImageIcon image = new ImageIcon();
        frame.setIconImage(image.getImage());

    } //Initializes JFrame

    public void setPlayer(Player player){
        this.player = player;
    }

    /**
     * This method displays the Slide slide onto the Jframe
     */
    public void display() {
        frame.getContentPane().removeAll(); // Clears the content currently on the jframe

        frame.repaint();

        /* creates a scrolling display that contains the text and buttons for the game */
        JScrollPane textScroll = new JScrollPane(createTextPanel());
        JScrollPane buttonScroll = new JScrollPane(createButtonPanel());
        JScrollPane titleScroll = new JScrollPane(createTitlePanel());
        frame.add(textScroll);
        frame.add(buttonScroll, BorderLayout.SOUTH);
        frame.add(titleScroll, BorderLayout.NORTH);
        frame.setVisible(true);

    }

    /**
     * This method creates a panel that contains the text and buttons of the slide of the game
     * @return JPanel This is the panel that is to be added and displayed on the jframe
     */
    // This is the main panel with the textarea inside of it
    public JPanel createTextPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        player.checkValidChoices();
        JTextArea textArea = createTextArea(panel);

        panel.setPreferredSize(new Dimension(100, 100));

        panel.add(textArea);
        return panel;
    }

    // This is the bottom panel where the button are
    public JPanel createButtonPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);

        int num = createButtons(panel);
        panel.setLayout(new GridLayout(num, 1));
        panel.setPreferredSize(new Dimension(100, num * 50));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        return panel;
    }

    // This does the animation of the text showing one letter at a time
    public void addAnimation(JTextArea ta) {
        String text = player.currentSlide.prompt;
        Timer timer = new Timer(30, new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                i = ta.getText().length();
                if(i >= text.length()){
                    ((Timer) e.getSource()).stop();
                }else {
                    String textAreaText = ta.getText();
                    textAreaText += text.charAt(i);
                    ta.setText(textAreaText);
                }
            }
        });

        ta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ta.setText(text);
            }
        });
        timer.start();
    }

    // The title panel is where the title of the scene is displayed if we want one
    // Temporary
    public JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.green);
        panel.setPreferredSize(new Dimension(100, 100));
        JLabel jlabel = new JLabel("Scene Title");
        jlabel.setFont(new Font("Verdana",Font.PLAIN,30));
        jlabel.setForeground(Color.black);
        panel.add(jlabel);
        return panel;
    }

    // The textarea is the main area where the scene text is displayed
    public JTextArea createTextArea(JPanel panel) {
        JTextArea t1;
        t1 = new JTextArea("", 20, 10);
        t1.setLineWrap(true);
        t1.setForeground(Color.white);
        t1.setBackground(Color.black);
        t1.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        Border border = BorderFactory.createLineBorder(Color.black, 10);
        t1.setBorder(border);
        // Temporary font
        try{
            File font_file = new File("src/main/java/client/PatrickHand-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font sizedFont = font.deriveFont(30f);
            t1.setFont(sizedFont);
        }catch (FontFormatException | IOException e){
            t1.setFont(new Font("comic sans", Font.PLAIN, 25));
        }
        addAnimation(t1);
        return t1;
    }

    // This gets the red and white arrows before each button saved as arrow.png and redArrow.png
    public ImageIcon createIcon(String filename){
        ImageIcon white = new ImageIcon("src/main/java/client/" + filename);
        Image image = white.getImage();
        Image newImg = image.getScaledInstance(25, 20,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    // This adds the listeners to the buttons so that the buttons change to red when hovered over and goes to the
    // next scene when clicked
    public void addListeners(JButton b, ImageIcon redArrow, ImageIcon arrow, Decision decision){
        b.addActionListener(e -> {
            player.currentSlide = decision.target;
            player.playScene();
        });
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setForeground(Color.RED);
                b.setIcon(redArrow);
            }
            public void mouseExited(MouseEvent e) {
                b.setForeground(Color.white);
                b.setIcon(arrow);
            }});
    }

    // This creates all the buttons for each scene
    public int createButtons(JPanel panel) {
        int count = 0;
        for (Decision decision : player.currentValidDecisions) {
            count++;
            JButton b = new JButton();

            final ImageIcon arrow = createIcon("arrow.png");
            final ImageIcon redArrow = createIcon("redArrow.png");

            b.setIcon(arrow);
            b.setText(decision.text);
            addListeners(b, redArrow, arrow, decision);
            b.setFocusable(false);
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            b.setFont(new Font("Rockwell", Font.PLAIN, 25));
            b.setBackground(Color.black);
            b.setForeground(Color.white);
            b.setBorder(null);
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.add(b);
        }
        return count;
    }

    public static void main(String[] args){
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, RDFLoadTest.returnGame());
        p.playGame();
    }

}