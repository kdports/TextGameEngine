package buttons;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import rdf.RDFLoadToStudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * A button that allows the user to load a project they had previously been working on.
 */
public class LoadButton extends MenuButton {

    public LoadButton(Scene window, EditorGame editorGame){
        super();

        this.setText("Load");
        this.setLayoutY(320);

        // The on-click button action
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
