package client;

import buttons.*;

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

    public SidebarButtons(Scene window, EditorGame editorGame) {
        Button createNewSlideButton = new NewSlideButton();
        Button playTestButton = new PlayTestButton();
        Button saveButton = new SaveButton(window, editorGame);
        Button loadButton = new LoadButton(window, editorGame);

        this.addAll(Arrays.asList(createNewSlideButton, playTestButton, saveButton, loadButton));
    }
}
