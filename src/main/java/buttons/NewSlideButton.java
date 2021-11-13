package buttons;

import handlers.Handlers;

public class NewSlideButton extends MenuButton {

    public NewSlideButton() {
        this.setText("Add Slide");
        this.setId("button-add-slide");
        this.setLayoutY(10);
        this.setOnMouseClicked(Handlers.createNewSlideHandler::execute);
    }
}
