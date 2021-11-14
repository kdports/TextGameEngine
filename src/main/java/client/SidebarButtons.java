package client;

import buttons.*;

import entities.EditorGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.List;

public class SidebarButtons extends ArrayList<Button> {

    public SidebarButtons(Scene window, EditorGame editorGame) {
        Button createNewSlideButton = new NewSlideButton();
        Button playTestButton = new PlayTestButton(editorGame);
        Button saveButton = new SaveButton(window, editorGame);
        Button loadButton = new LoadButton(window, editorGame);

        Button[] buttons = new Button[]{createNewSlideButton, playTestButton, saveButton, loadButton};
        this.addAll(List.of(buttons));
    }
}
