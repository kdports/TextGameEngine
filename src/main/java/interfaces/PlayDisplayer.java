package interfaces;
import entities.Player;
import entities.Slide;

public interface PlayDisplayer {
    void display(Slide slide);
    void setPlayer(Player player);
}
