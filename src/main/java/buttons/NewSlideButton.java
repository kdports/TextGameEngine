package buttons;

import handlers.Handlers;

/**
 * The button that allows the user to create a new slide.
 */
public class NewSlideButton extends MenuButton {

    public NewSlideButton() {
        this.setText("Add Slide");
        this.setId("button-add-slide");
        this.setLayoutY(10);
        this.setOnMouseClicked(Handlers.createNewSlideHandler::execute);
    }
}
