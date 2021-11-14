package buttons;

import client.GuiDecision;
import client.GuiSlide;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import rdf.RDFLoadToStudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class LoadButton extends MenuButton {

    /**
     * Creates a Button instance of the load button that is displayed in the game.
     * Also handles what happens when the button is clicked (loading up an
     * editor from file)
     *
     * @param window - The window in which the file explorer resides when clicking load
     * @param editorGame - The existing EditorGame instance that will be filled with
     *                   data from the incoming file
     */
    public LoadButton(Scene window, EditorGame editorGame){
        super();

        this.setText("Load");
        this.setLayoutY(320);

        // When button is clicked, open a file explorer and load in data from a file
        this.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Turtle File (.ttl)", "*.ttl");
            fileChooser.getExtensionFilters().add(exFilter);
            File location = fileChooser.showOpenDialog(window.getWindow());

            if (location != null){
                String path = location.getAbsolutePath();
                try {
                    RDFLoadToStudio loader = new RDFLoadToStudio(path);

                    EditorGame loadedEditorGame = loader.loadEditorGameFromFile();
                    editorGame.clearAll();

                    for (Map.Entry<Slide, GuiSlide> entry : loadedEditorGame.getAllEntriesSlide()) {
                        editorGame.connectSlideAndRenderableSlide(entry.getKey(), entry.getValue());

                        // SETTING FIRST SLIDE ----------------------
                        if (entry.getKey().returnFirstSlide().get()){
                            editorGame.firstSlide = entry.getKey();
                        }
                    }

                    for (Map.Entry<Decision, GuiDecision> entry : loadedEditorGame.getAllEntriesDecision()) {
                        editorGame.connectDecisionAndRenderableDecision(entry.getKey(), entry.getValue());
                    }}
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
