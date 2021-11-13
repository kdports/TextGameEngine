package client;

import entities.EditorGame;
import handlers.Handlers;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import rdf.RDFSave;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SidebarButtons extends ArrayList<Button> {
    private final int buttonWidth = 120;
    private final int buttonHeight = 90;

    public SidebarButtons(Scene window, EditorGame editorGame) {
        Button createNewSlideButton = this.createAddNewSlideButton();
        Button playTestButton = this.createPlayTestButton();
        Button saveButton = this.createSaveButton(window, editorGame);
        Button loadButton = this.createLoadButton();

        this.add(createNewSlideButton);
        this.add(playTestButton);
        this.add(saveButton);
        this.add(loadButton);
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

    public Button createSaveButton(Scene window, EditorGame editorGame) {
        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #c72c41;");
        btnSave.setLayoutX(10);
        btnSave.setLayoutY(215);
        btnSave.setPrefWidth(buttonWidth);
        btnSave.setPrefHeight(buttonHeight);

        btnSave.setOnMouseClicked(event -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("GameProject");
            FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("description", "*.ttl");
            fileChooser.getExtensionFilters().add(exFilter);
            File location = fileChooser.showSaveDialog(window.getWindow());

            if (location != null){
                String path = location.getAbsolutePath();
                RDFSave rdfSave = new RDFSave();
                try {
                    rdfSave.saveToTrig(editorGame, path);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        return btnSave;
    }

    public Button createLoadButton() {
        Button btnLoad = new Button("Load");

        btnLoad.setStyle("-fx-background-color: #c72c41;");
        btnLoad.setLayoutX(10);
        btnLoad.setLayoutY(320);
        btnLoad.setPrefWidth(buttonWidth);
        btnLoad.setPrefHeight(buttonHeight);

        return btnLoad;
    }

}
