package buttons;

import entities.EditorGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A wrapper for all the buttons drawn on the sidebar of the app.
 */
public class SidebarButtons extends ArrayList<Button> {

    /**
     * Creates button instances of all 4 buttons and adds them to the editor
     *
     * @param window - The window in which the file explorer resides
     * @param editorGame - The existing EditorGame instance
     */
    public SidebarButtons(Scene window, EditorGame editorGame) {
        Button createNewSlideButton = new NewSlideButton();
        Button playTestButton = new PlayTestButton(editorGame);
        Button saveButton = new SaveButton(window, editorGame);
        Button loadButton = new LoadButton(window, editorGame);

        this.addAll(Arrays.asList(createNewSlideButton, playTestButton, saveButton, loadButton));
    }
}
