package client;

import handlers.Handlers;
import javafx.scene.control.Button;
import java.util.ArrayList;

public class SidebarButtons extends ArrayList<Button> {
    private final int buttonWidth = 120;
    private final int buttonHeight = 90;

    public SidebarButtons() {
        Button createNewSlideButton = this.createAddNewSlideButton();
        Button playTestButton = this.createPlayTestButton();
        Button saveButton = this.createSaveButton();

        this.add(createNewSlideButton);
        this.add(playTestButton);
        this.add(saveButton);
    }

    public Button createAddNewSlideButton() {
        Button btnAddSlide = new Button();
        btnAddSlide.setText("Add Slide");
        btnAddSlide.setId("button-add-slide");
        btnAddSlide.setLayoutX(10);
        btnAddSlide.setLayoutY(10);
        btnAddSlide.setPrefWidth(buttonWidth);
        btnAddSlide.setPrefHeight(buttonHeight);
        btnAddSlide.setStyle("-fx-background-color: #c72c41; ");
        btnAddSlide.setOnMouseClicked(Handlers.createNewSlideHandler::execute);

        return btnAddSlide;
    }

    public Button createPlayTestButton() {
        Button btnPlaytest = new Button("Play Test");
        btnPlaytest.setStyle("-fx-background-color: #c72c41;");
        btnPlaytest.setLayoutX(10);
        btnPlaytest.setLayoutY(112.5);
        btnPlaytest.setPrefWidth(buttonWidth);
        btnPlaytest.setPrefHeight(buttonHeight);
        // btnPlaytest.setOnAction(new TestHandler());
        return btnPlaytest;
    }

    public Button createSaveButton() {
        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #c72c41;");
        btnSave.setLayoutX(10);
        btnSave.setLayoutY(215);
        btnSave.setPrefWidth(buttonWidth);
        btnSave.setPrefHeight(buttonHeight);
        // btnSave.setOnAction(new SaveHandler());
        return btnSave;
    }
}
