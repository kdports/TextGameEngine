package buttons;

import handlers.Handlers;

/**
 * The button that allows the user to create a new slide.
 */
public class NewSlideButton extends MenuButton {

    /**
     * Creates a Button instance of the new slide button that is displayed in the game.
     * Calls the respective handler when the button is clicked
     */
    public NewSlideButton() {
        this.setText("Add Slide");
        this.setId("button-add-slide");
        this.setLayoutY(10);
        this.setOnMouseClicked(Handlers.createNewSlideHandler::execute);
    }
}
