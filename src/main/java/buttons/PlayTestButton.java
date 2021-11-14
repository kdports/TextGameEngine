package buttons;

import javafx.scene.control.Button;

/**
 * The button that allows the user to play-test the game they are currently creating.
 */
public class PlayTestButton extends MenuButton {

    public PlayTestButton() {
        this.setText("Play Test");
        this.setLayoutY(112.5);
        // btnPlaytest.setOnAction(new TestHandler());
    }

}
