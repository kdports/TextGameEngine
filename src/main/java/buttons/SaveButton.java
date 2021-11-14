package buttons;

import entities.EditorGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import rdf.RDFSave;

import java.io.File;
import java.io.FileNotFoundException;

public class SaveButton extends MenuButton {

    /**
     * Creates a Button instance of the save button that is displayed in the game.
     * Also handles what happens when the button is clicked (saving an editor
     * to a file)
     *
     * @param window - The window in which the file explorer resides when clicking save
     * @param editorGame - The existing EditorGame instance that will have its data
     *                   saved to a file
     */
    public SaveButton(Scene window, EditorGame editorGame) {
        super();

        this.setText("Save");
        this.setLayoutY(215);

        this.setOnMouseClicked(event -> {

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
    }
}
