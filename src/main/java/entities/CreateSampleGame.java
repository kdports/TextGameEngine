package entities;

import java.util.ArrayList;

public class CreateSampleGame {

    public static Game returnGame() {
        /*
        Creates a simple game
         */
        Decision d1 = new Decision("You walk down the brightly lit path.");
        Decision d2 = new Decision("You enter the dark and scary forest.");
        ArrayList<Decision> a1 = new ArrayList<>();
        a1.add(d1);
        a1.add(d2);

        Decision d12 = new Decision("Head back and take another path.");
        ArrayList<Decision> a2 = new ArrayList<>();
        a2.add(d12);
        Decision d13 = new Decision("You take the candy.");
        a2.add(d13);
        Decision d14 = new Decision("You refuse the candy.");
        a2.add(d14);

        ArrayList<Decision> a3 = new ArrayList<>();
        Decision d32 = new Decision("Dodge to the right.");
        Decision d31 = new Decision("Dodge to the left.");
        a3.add(d32);
        a3.add(d31);

        ArrayList<Decision> a4 = new ArrayList<>();
        Decision d42 = new Decision("You eat the candy.");
        Decision d41 = new Decision("You put it in your back pocket.");
        a4.add(d42);
        a4.add(d41);

        Slide end = new Slide(1, "Wrong choice, the bear kills you.");
        Slide end0 = new Slide(2, "You fall and hit your head on a rock and die.");
        Slide s1 = new Slide(3, "You are traveling along a path when it splits into two paths. Which do you choose?", a1);
        Slide s2 = new Slide(4, "You walk along the bright path when you come across a man with an axe, he offers you candy, do you take it?", a2);
        Slide s3 = new Slide(5, "A monster lunges at you, do you dodge to the left or to the right?", a3);
        Slide s4 = new Slide(6, "You take the candy, do you eat it or save it for later?", a4);
        Slide end2 = new Slide(7, "The candy is poisoned and kills you.");
        Slide end3 = new Slide(8, "The man is angered by your refusal and kills you.");
        Slide end11 = new Slide(9, "You obtained a candy! But then you died.");


        d1.setTarget(s2);
        d2.setTarget(s3);


        d12.setTarget(s1);
        d13.setTarget(s4);
        d14.setTarget(end3);

        d32.setTarget(end0);
        d31.setTarget(end);

        d42.setTarget(end2);
        d41.setTarget(end11);

        return new Game(s1);
    }
}
