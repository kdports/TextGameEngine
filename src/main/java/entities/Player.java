package entities;
import interfaces.PlayDisplayer;
import java.util.ArrayList;

public class Player {
    public Slide currentSlide;
    public PlayDisplayer dp;
    public Game game;
    public ArrayList<Decision> currentValidDecisions;

    public Player(PlayDisplayer dp, Game game){
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
        dp.display();
    }

    /**
     * CURRENTLY JUST A PLACEHOLDER
     * takes in a list of decisions and return a subset of the list of valid decisions
     */
    public void checkValidChoices() {
        ArrayList<Decision> ar = new ArrayList<>();
        for (Decision d: this.currentSlide.decisions){
            if(checkChoice(d)){
                ar.add(d);
            }
        }
        this.currentValidDecisions = ar;
    }

    /**
     * CURRENTLY JUST A PLACEHOLDER
     * takes in a decision and checks if its valid
     * @param d a decision
     * @return boolean returns whether the decision is valid
     */
    public static boolean checkChoice(Decision d){
        return d != null;
    }


}