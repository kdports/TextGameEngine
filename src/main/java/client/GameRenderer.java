package client;

import entities.*;
import interfaces.Displayer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * The class that is in charge of rendering the game
 */
public class GameRenderer implements Displayer {
    JFrame frame; // Creates JFrame that the gamerenderer will use to display the window
    Player player;// The player that plays the game to render
    Theme theme;

    int animationSpeed;

    /**
     * Creates the gamerenderer
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
     * Sets the player for the game
     * @param player player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * This method displays the slide onto the Jframe
     */
    public void display() {
        frame.getContentPane().removeAll(); // Clears the content currently on the jframe

        frame.repaint();

        /* creates a scrolling display that contains the text and buttons for the game */
        JScrollPane textScroll = new JScrollPane(createTextPanel());
        JScrollPane buttonScroll = new JScrollPane(createButtonPanel());
        frame.add(textScroll);
        frame.add(buttonScroll, BorderLayout.SOUTH);
        frame.setJMenuBar(createMenu());
        frame.setVisible(true);

    }

    /**
     * This method creates a panel that contains the text and buttons of the slide of the game
     *
     * @return JPanel This is the panel that is to be added and displayed on the jframe
     */
    public JPanel createTextPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        player.checkValidChoices();
        JTextArea textArea = createTextArea(panel);

        panel.setPreferredSize(new Dimension(100, 100));

        panel.add(textArea);
        return panel;
    }

    /**
     * Creates the panel with all the buttons to add to the screen
     * @return the panel with the buttons
     */
    public JPanel createButtonPanel() {

        JPanel panel = new JPanel();
        panel.setBackground(theme.backgroundColor);

        int num = createButtons(panel);
        panel.setLayout(new GridLayout(num, 1));
        panel.setPreferredSize(new Dimension(100, num * 50));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        return panel;
    }

    /**
     * creates the menu bar at the top of the window
     * @return The menu bar
     */
    public JMenuBar createMenu() {
        JMenuBar mb = new JMenuBar();
        JMenu themes = new JMenu("Themes");
        for (String t : theme.themeList) {
            JMenuItem th = new JMenuItem(t);
            th.addActionListener(e -> {
                theme.setTheme(t);
                display();
            });
            themes.add(th);
        }
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
            speed_num += 5;
            speed_num *= 2;
            animation.add(sp);
        }
        mb.add(themes);
        mb.add(animation);
        return mb;
    }

    /**
     * Animates the text by displaying the slide text as if someone was typing it
     * @param ta text area to animate
     */
    public void addAnimation(JTextArea ta) {
        String text = player.currentSlide.prompt;
        Timer timer = new Timer(animationSpeed, new ActionListener() {
            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                i = ta.getText().length();
                if (i >= text.length()) {
                    ((Timer) e.getSource()).stop();
                } else {
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

    /**
     * The method creates a main text area for the game screen
     * @param panel the panel to create the text area in
     * @return the text area
     */
    public JTextArea createTextArea(JPanel panel) {
        JTextArea t1;
        t1 = new JTextArea("", 20, 10);
        t1.setLineWrap(true);
        t1.setForeground(theme.textColor);
        t1.setBackground(theme.backgroundColor);
        t1.setEditable(false);
        t1.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        Border border = BorderFactory.createLineBorder(theme.backgroundColor, 10);
        t1.setBorder(border);
        try {
            File font_file = new File("src/main/resources/player/OpenSans-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font sizedFont = font.deriveFont(25f);
            t1.setFont(sizedFont);
        } catch (FontFormatException | IOException e) {
            t1.setFont(new Font("comic sans", Font.PLAIN, 25));
        }
        addAnimation(t1);
        return t1;
    }

    /**
     * Creates and imageicon from the filename and recolors the image to a certain color
     * @param filename filename to load the icon image from
     * @param color color to color the image
     * @return the image
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
     * Adds listeners to the button choices so that they change color when hovered
     * @param b button to add listeners to
     * @param hoverArrow the image of the hovered arrow
     * @param arrow the imagicon of the normal arrow
     */
    public void addListeners(JButton b, ImageIcon hoverArrow, ImageIcon arrow) {
        b.addMouseListener(new MouseAdapter() {
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
     * Adds which slide to go to when the button is clicked
     * @param b the button that will be clicked
     * @param decision the decision linked to the button
     */
    private void addDestinationAction(JButton b, Decision decision) {
        b.addActionListener(e -> {
            player.currentSlide = decision.target;
            player.playScene();
        });
    }

    /**
     * Creates buttons for all of the decisions in a slide
     * @param panel the panel to add the buttons to
     * @return the number of buttons
     */
    public int createButtons(JPanel panel) {
        int count = 0;
        final ImageIcon arrow = createIcon("redArrow.png", theme.textColor);
        final ImageIcon redArrow = createIcon("redArrow.png", theme.textHoverColor);
        if (player.currentValidDecisions.size() == 0) {
            JButton b = createButton(arrow);
            b.setText("Replay the game?");
            addListeners(b, redArrow, arrow);
            b.addActionListener(e -> {
                player.currentSlide = player.game.firstSlide;
                player.playScene();
            });
            panel.add(b);
            return 1;
        }
        for (Decision decision : player.currentValidDecisions) {
            count++;

            JButton b = createButton(arrow);
            b.setText(decision.text);
            addDestinationAction(b, decision);
            addListeners(b, redArrow, arrow);
            panel.add(b);
        }
        return count;
    }

    /**
     * Creates 1 button that is styled as one of the decisions for a slide
     * @param arrow the image to appear at the start of the button
     * @return the button
     */
    private JButton createButton(ImageIcon arrow) {
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
     * Turns an image icon into a buffered image
     * @param icon the image icon to transform
     * @return the image icone as a bufferedImage
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
     * Changes all of the colored pixels in an BufferedImage to another color
     * @param image The image to recolor
     * @param color The color to recolor
     * @return The recolored image
     */
    private static ImageIcon colorImage(BufferedImage image, Color color) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = color.getRed();
                pixels[1] = color.getGreen();
                pixels[2] = color.getBlue();
                raster.setPixel(xx, yy, pixels);
            }
        }
        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, RDFLoadTest.returnGame());
        p.playGame();
    }

}