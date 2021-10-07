package entities;

public class Player {
    Slide currentSlide;

    public static void playGame(Game game) {
        //
    }

    public void choiceMade(Decision choice) {
        this.currentSlide = choice.target;
    }
}