package buttons;

import client.DisplayGame.GameRenderer;
import client.GuiSlide.GuiSlide;
import entities.*;
import interfaces.PlayDisplayer;
import javafx.scene.control.ScrollPane;

import java.util.Map;

import static java.lang.Math.abs;

/**
 * The button that allows the user to play-test the game they are currently creating.
 */
public class PlayTestButton extends MenuButton {

    /**
     * Creates a Button instance of the playtest button that is displayed in the game.
     * Also handles what happens when the button is clicked (running the game
     * in the gameplayer)
     *
     * @param editorGame - The existing EditorGame instance that will be used to
     *                   populate a game instance with existing data
     */
    public PlayTestButton(EditorGame editorGame, ScrollPane scrollPane) {
        super(scrollPane);
        this.setText("Play Test");
        this.setLayoutY(112.5);
        scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> this.setLayoutY(abs(newvalue.getMinY()) + 112.5)
        );

        this.setOnMouseClicked(event -> {
            PlayDisplayer playDisplayer = new GameRenderer();
            Game game = new Game();
            Player player = new Player(playDisplayer, game);

            // ADD ALL SLIDES TO GAME
            for (Map.Entry<Slide, GuiSlide> entry : editorGame.getAllEntriesSlide()) {
                Slide slide = entry.getKey();

                // Check for a slide with no incoming decisions, set it as first slide.
                if (slide == editorGame.firstSlide) {
                    game.firstSlide = slide;
                }
                game.addSlide(slide);
            }
            player.playGame();
        });
    }

}
