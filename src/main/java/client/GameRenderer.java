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
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class GameRenderer implements Displayer {
    JFrame frame; // Creates JFrame that the gamerenderer will use to display the window
    Player player;
    Theme theme;

    public GameRenderer() {
        frame = new JFrame("Game");
        // Place holder name for now
        frame.setTitle("Temp Title");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the image in the top left, currently no image
        ImageIcon image = new ImageIcon();
        frame.setIconImage(image.getImage());

        theme = new Theme();
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
        frame.setJMenuBar(createMenu());
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
        panel.setBackground(theme.backgroundColor);

        int num = createButtons(panel);
        panel.setLayout(new GridLayout(num, 1));
        panel.setPreferredSize(new Dimension(100, num * 50));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        return panel;
    }

    public JMenuBar createMenu(){
        JMenuBar mb = new JMenuBar();
        JMenu themes = new JMenu("Themes");
        for(String t : theme.themeList){
            JMenuItem th = new JMenuItem(t);
            th.addActionListener(e -> {
                theme.setTheme(t);
                display();
            });
            themes.add(th);
        }
        mb.add(themes);
        return mb;
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
        t1.setForeground(theme.textColor);
        t1.setBackground(theme.backgroundColor);
        t1.setEditable(false);
        t1.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        Border border = BorderFactory.createLineBorder(theme.backgroundColor, 10);
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
    public ImageIcon createIcon(String filename, Color color){
        ImageIcon white = new ImageIcon("src/main/java/client/" + filename);
        Image image = white.getImage();
        Image newImg = image.getScaledInstance(25, 20,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon white1 = new ImageIcon(newImg);
        BufferedImage img = intoBuffer(white1);
        return colorImage(img, color);
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
                b.setForeground(theme.textHoverColor);
                b.setIcon(redArrow);
            }
            public void mouseExited(MouseEvent e) {
                b.setForeground(theme.textColor);
                b.setIcon(arrow);
            }});
    }

    // This creates all the buttons for each scene
    public int createButtons(JPanel panel) {
        int count = 0;
        for (Decision decision : player.currentValidDecisions) {
            count++;
            JButton b = new JButton();

            final ImageIcon arrow = createIcon("redArrow.png", theme.textColor);
            final ImageIcon redArrow = createIcon("redArrow.png", theme.textHoverColor);

            b.setIcon(arrow);
            b.setText(decision.text);
            addListeners(b, redArrow, arrow, decision);
            b.setFocusable(false);
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            b.setFont(new Font("Rockwell", Font.PLAIN, 25));
            b.setBackground(theme.backgroundColor);
            b.setForeground(theme.textColor);
            b.setBorder(null);
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.add(b);
        }
        return count;
    }
    public BufferedImage intoBuffer(ImageIcon icon){
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
// paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.dispose();
        return bi;
    }

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

    public static void main(String[] args){
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, RDFLoadTest.returnGame());
        p.playGame();
    }

}