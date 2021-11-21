package entities;
import interfaces.PlayDisplayer;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class that is in charge of playing the game
 */
public class Player {
    public Slide currentSlide;
    public PlayDisplayer dp;
    public Game game;
    public ArrayList<Decision> currentValidDecisions;
    private HashSet<Decision> pastChosenDecisions;

    /**
     * Constructs a player
     * @param dp - The displayer of the game
     * @param game - The game to play
     */
    public Player(PlayDisplayer dp, Game game){
        this.dp = dp;
        this.game = game;
        dp.setPlayer(this);
        this.pastChosenDecisions = new HashSet();
    }

    /**
     * Plays the game from the first slide
     */
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
     * Takes in a list of decisions and return a subset of the list of valid decisions
     */
    public void checkValidChoices() {
        ArrayList<Decision> ar = new ArrayList<>();
        for (Decision d: this.currentSlide.outgoingDecisions){
            if(checkChoice(d)){
                ar.add(d);
            }
        }
        this.currentValidDecisions = ar;
    }

    /**
     * Rakes in a decision and checks if its valid
     *
     * @param d a decision
     * @return boolean returns whether the decision is valid
     */
    public static boolean checkChoice(Decision d){
        return d != null;
    }

    /**
     * Checks if the given decision is in the list of previously chosen decisions
     * @param d a decision
     * @return boolean whether the decision is included
     */
    public boolean IsInPastChosenDecisions(Decision d) { return this.pastChosenDecisions.contains(d); }

    /**
     * Adds the inputted decision to the set of previously chosen decisions
     * @param d A decision that has been chosen
     */
    public void AddToPastChosenDecisions(Decision d) { this.pastChosenDecisions.add(d); }

    /**
     * Returns the entire set of past chosen decisions. Probably just for testing
     * @return pastChosenDecisions
     */
    public HashSet GetPastChosenDecisions() { return this.pastChosenDecisions; }
}