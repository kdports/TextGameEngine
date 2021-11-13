package buttons;

import client.GuiDecision;
import client.GuiSlide;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import rdf.RDFLoadToStudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class LoadButton extends MenuButton {

    public LoadButton(Scene window, EditorGame editorGame){
        super();

        this.setText("Load");
        this.setLayoutY(320);

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
