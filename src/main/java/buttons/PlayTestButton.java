package buttons;

import client.GameRenderer;
import client.GuiDecision;
import client.GuiSlide;
import entities.*;
import interfaces.PlayDisplayer;
import javafx.scene.control.Button;
import org.apache.jena.rdf.model.Resource;
import rdf.TGEO;

import java.util.Map;

public class PlayTestButton extends MenuButton {

    public PlayTestButton(EditorGame editorGame) {
        this.setText("Play Test");
        this.setLayoutY(112.5);

        this.setOnMouseClicked(event -> {
            PlayDisplayer playDisplayer = new GameRenderer();
            Game game = new Game();
            Player player = new Player(playDisplayer, game);

            // ADD ALL SLIDES TO GAME
            boolean first = true;
            for (Map.Entry<Slide, GuiSlide> entry : editorGame.getAllEntriesSlide()) {
                Slide slide = entry.getKey();
                if (first) {
                    game.firstSlide = slide;
                    first = false;
                }
                game.addSlide(slide);
            }
            player.playGame();
        });
    }

}
