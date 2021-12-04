package buttons;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import client.ThemeColours;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import rdf.RDFLoadToStudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import static java.lang.Math.abs;

/**
 * A button that allows the user to load a project they had previously been working on.
 */
public class LoadButton extends MenuButton {

    /**
     * Creates a Button instance of the load button that is displayed in the game.
     * Also handles what happens when the button is clicked (loading up an
     * editor from file)
     *  @param window - The window in which the file explorer resides when clicking load
     * @param editorGame - The existing EditorGame instance that will be filled with
     * @param theme
     */
    public LoadButton(Scene window, EditorGame editorGame, ScrollPane scrollPane, ThemeColours theme){
        super(scrollPane);

        this.setStyle("-fx-background-color: " + theme.active.sidebarColour + ";" +
                "-fx-text-fill: " + theme.active.textColour);
        this.setText("Load");
        this.setLayoutY(320);
        scrollPane.vvalueProperty().addListener((observable, oldvalue, newvalue) -> {
                    this.setLayoutY(newvalue.doubleValue() + 320);
                }
        );

        scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> this.setLayoutY(abs(newvalue.getMinY()) + 320)
        );



        // When button is clicked, open a file explorer and load in data from a file
        this.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Turtle File (.ttl)", "*.ttl");
            fileChooser.getExtensionFilters().add(exFilter);
            File location = fileChooser.showOpenDialog(window.getWindow());

            // If the user chose a valid file to load from, load it in.
            if (location != null){
                String path = location.getAbsolutePath();
                try {
                    RDFLoadToStudio loader = new RDFLoadToStudio(path);
                    EditorGame loadedEditorGame = loader.loadEditorGameFromFile();
                    // Clear the data so that whatever work they currently had would be wiped.
                    editorGame.clearAll();

                    // Fill the editorGame maps with the loaded data.
                    for (Map.Entry<Slide, GuiSlide> entry : loadedEditorGame.getAllEntriesSlide()) {
                        editorGame.connectSlideAndRenderableSlide(entry.getKey(), entry.getValue());

                        // SETTING FIRST SLIDE ----------------------
                        if (entry.getKey().getObservableFirstSlide().get()){
                            editorGame.firstSlide = entry.getKey();
                        }
                    }
                    for (Map.Entry<Decision, GuiDecision> entry : loadedEditorGame.getAllEntriesDecision()) {
                        editorGame.connectDecisionAndRenderableDecision(entry.getKey(), entry.getValue());
                    }
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
