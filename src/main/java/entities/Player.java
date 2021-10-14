package entities;
import client.GameRenderer;
import interfaces.Displayer;

import java.util.ArrayList;

public class Player {
    public Slide currentSlide;
    public Displayer dp;
    public Game game;

    public Player(Displayer dp, Game game){
        this.dp = dp;
        this.game = game;
        dp.setPlayer(this);
    }
    public void playGame() {
        currentSlide = game.firstSlide;
        playScene();
    }

    /**
     * Method to play the current scene,
     * Will be used to keep track of decision and other game elements but will be
     * implemented later
     */
    public void playScene(){
        dp.display(currentSlide);
    }

    // Checks the choices to see if the player is allowed to go to that slide
    // Not sure how we are going to check the prerequisites just yet
    public static ArrayList<Decision> checkValidChoices(ArrayList<Decision> decisions) {
//        ArrayList<Decision> ar = new ArrayList<>();
//        for (Decision d: decisions){
//            if(checkChoice(d)){
//                ar.add(d);
//            }
//        }
//        return ar;
        return decisions;
    }

    // Checks each choice to see if the prerequisites are met
    // Currently just returns every choice
    public static boolean checkChoice(Decision d){
//        if(true){
//            return true;
//        }else{
//            return false;
//        }
        return true;
    }

    // Initiates a simple game
    public static void main(String[] args) {
    }
}