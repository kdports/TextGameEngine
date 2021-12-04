package buttons;

import client.ThemeColours;
import entities.EditorGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A wrapper for all the buttons drawn on the sidebar of the app.
 */
public class SidebarButtons extends ArrayList<Button> {

    /**
     * Creates button instances of all 4 buttons and adds them to the editor
     *  @param window - The window in which the file explorer resides
     * @param editorGame - The existing EditorGame instance
     * @param root
     */
    public SidebarButtons(Scene window, EditorGame editorGame, ScrollPane scrollPane, ThemeColours theme, Pane root) {
        Button createNewSlideButton = new NewSlideButton(scrollPane, theme);
        Button playTestButton = new PlayTestButton(editorGame, scrollPane, theme);
        Button saveButton = new SaveButton(window, editorGame, scrollPane, theme);
        Button loadButton = new LoadButton(window, editorGame, scrollPane, theme);
        Button themeButton = new ThemeButton(theme, scrollPane, root);

        this.addAll(Arrays.asList(createNewSlideButton, playTestButton, saveButton, loadButton, themeButton));
    }
}
