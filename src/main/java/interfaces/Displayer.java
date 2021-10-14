package interfaces;
import entities.Player;
import entities.Slide;

public interface Displayer {
    void display(Slide slide);
    void setPlayer(Player player);
}
