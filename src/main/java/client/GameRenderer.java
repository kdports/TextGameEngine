package main.java.client;
import main.java.entities.*;
import javax.swing.*;
import java.util.ArrayList;

// GUI class!!!11!11!!!!
public class GameRenderer {
    public GameRenderer() {
    }
    public void render(Game game){
        JFrame f= new JFrame("Test");
        f.setSize(500,500);
        f.setResizable(true);
        JTextArea t1;
        t1=new JTextArea(game.firstSlide.prompt);
        t1.setLineWrap(true);
        //t1.setBounds(20,20, 200,200);
        JScrollPane scroll = new JScrollPane(t1);
        f.add(scroll);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //f.setSize(400,400);
        //f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Decision d1 = new Decision("back");
        ArrayList<Decision> a1 = new ArrayList<>();
        a1.add(d1);
        Decision d2 = new Decision("back");
        ArrayList<Decision> a2 = new ArrayList<>();
        a2.add(d2);
        Slide s1 = new Slide("Thicker than a bowl of oatmeal p1", a1);
        Slide s2 = new Slide("Thicker than a bowl of oatmeal p2", a2);
        d1.setTarget(s2);
        d2.setTarget(s1);

        Game game = new Game(s1);
        game.addSlide(s1);
        game.addSlide(s2);

        GameRenderer gr = new GameRenderer();
        gr.render(game);
    }
}