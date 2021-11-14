package buttons;

import client.GameRenderer;
import client.GuiSlide;
import entities.*;
import interfaces.PlayDisplayer;

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

                // Check for a slide with no incoming decisions, set it as first slide.
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
