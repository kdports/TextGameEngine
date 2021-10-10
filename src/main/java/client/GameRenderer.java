package main.java.client;
import main.java.entities.*;
import main.java.interfaces.DisplayGame;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
// GUI class!!!11!11!!!!
public class GameRenderer implements DisplayGame{
    public GameRenderer() {
    }
    // Currently just displays the game by entering stuff into the terminal
    public void display(Slide slide){
        Scanner response = new Scanner(System.in);
        System.out.println(slide.prompt);
        ArrayList<Decision> validDecisions;
        // It keeps count of the decisions and you make a decision by entering the number
        // next to the decision that you are making
        int count = 1;

        // Check if decisions are valid
        // TODO add the prerequisites
        validDecisions = Player.checkValidChoices(slide.decisions);
        for(Decision d: validDecisions){
            System.out.println("Enter the number next to the choice to choose.");
            System.out.print(count + " : ");
            System.out.println(d.text);
            count++;
        }

        // Does not ask for a decision if there are none, IE the last slide
        if (!validDecisions.isEmpty()){
            int t = response.nextInt();
            Player.currentSlide = validDecisions.get(t - 1).target;
        }

    }

}