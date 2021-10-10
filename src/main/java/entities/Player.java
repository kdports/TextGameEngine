package main.java.entities;
import main.java.client.GameRenderer;

import java.util.ArrayList;
import java.util.Scanner;
public class Player {
    public static Slide currentSlide;

    public static void playGame(Game game) {
        currentSlide = game.firstSlide;
        Scanner response = new Scanner(System.in);
        while(!currentSlide.decisions.isEmpty()){
            System.out.println(currentSlide.prompt);
            int count = 0;
            for(Decision d: currentSlide.decisions){

                System.out.print(count + " :");
                System.out.println(d.text);
                count++;
            }
            int t = response.nextInt();

            currentSlide = currentSlide.decisions.get(t).target;
        }
    }

    public void choiceMade(Decision choice) {
        currentSlide = choice.target;
    }

    public static void main(String[] args) {
        Decision d1 = new Decision("1");
        ArrayList<Decision> a1 = new ArrayList<>();
        a1.add(d1);
        Decision d12 = new Decision("12");
        ArrayList<Decision> a2 = new ArrayList<>();
        a2.add(d12);
        Decision d13 = new Decision("13");
        a2.add(d13);
        Decision d14 = new Decision("14");
        a2.add(d14);
        Slide s1 = new Slide("Thicker than a bowl of oatmeal p1", a1);
        Slide s2 = new Slide("rrrrrrr", a2);
        Slide s3 = new Slide("end");
        d1.setTarget(s2);
        d12.setTarget(s3);
        d13.setTarget(s1);
        d14.setTarget(s1);

        Game game = new Game(s1);
        game.addSlide(s1);
        game.addSlide(s2);
        game.addSlide(s3);

        playGame(game);
    }
}