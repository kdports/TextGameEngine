package main.java.entities;
import main.java.client.GameRenderer;

import java.util.ArrayList;

public class Player {
    public static Slide currentSlide;

    public static void playGame(Game game) {
        // Starts at the first slide
        currentSlide = game.firstSlide;
        GameRenderer gr = new GameRenderer();
        // Displays the slides until there are no more decisions to make
        while(!currentSlide.decisions.isEmpty()){
            // Tells the gamerenderer to display the slide
            // TODO make the gamerenderer display stuff
            gr.display(currentSlide);

        }
        // Displays the last slide
        gr.display(currentSlide);
    }

    // Checks the choices to see if the player is allowed to go to that slide
    // Not sure how we are going to check the prerequisites just yet
    public static ArrayList<Decision> checkValidChoices(ArrayList<Decision> decisions) {
        ArrayList<Decision> ar = new ArrayList<>();
        for (Decision d: decisions){
            if(checkChoice(d)){
                ar.add(d);
            }
        }
        return ar;
    }

    // Checks each choice to see if the prerequisites are met
    // Currently just returns every choice
    public static boolean checkChoice(Decision d){
        if(true){
            return true;
        }else{
            return false;
        }
    }

    // Initiates a simple game
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

        playGame(game);
    }
}