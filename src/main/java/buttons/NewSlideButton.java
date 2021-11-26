package buttons;

import client.ThemeColours;
import handlers.Handlers;

/**
 * The button that allows the user to create a new slide.
 */
public class NewSlideButton extends MenuButton {

    /**
     * Creates a Button instance of the new slide button that is displayed in the game.
     * Calls the respective handler when the button is clicked
     * @param theme
     */
    public NewSlideButton(ThemeColours theme) {
        this.setText("Add Slide");
        this.setStyle(theme.active.sidebarColour);
        this.setId("button-add-slide");
        this.setLayoutY(10);
        this.setOnMouseClicked(Handlers.createNewSlideHandler::execute);
    }
}
