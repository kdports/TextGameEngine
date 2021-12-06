package buttons;

import client.ThemeColours;
import handlers.Handlers;
import javafx.scene.control.ScrollPane;

import static java.lang.Math.abs;

/**
 * The button that allows the user to create a new slide.
 */
public class NewSlideButton extends MenuButton {

    /**
     * Creates a Button instance of the new slide button that is displayed in the game.
     * Calls the respective handler when the button is clicked
     *
     * @param theme - the theme of the gui
     * @param scrollPane - the pane where the buttons are located
     */
    public NewSlideButton(ScrollPane scrollPane, ThemeColours theme) {
        super(scrollPane);
        this.setText("Add Slide");
        this.setId("button-add-slide");
        this.setTheme(theme);
        this.setLayoutY(10);
        scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> this.setLayoutY(abs(newvalue.getMinY()) + 10)
        );
        this.setOnMouseClicked(event -> {Handlers.createNewSlideHandler.execute(this.getLayoutX(), this.getLayoutY(), theme);});
    }
}
