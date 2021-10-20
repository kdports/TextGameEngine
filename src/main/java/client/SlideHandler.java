package client;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class SlideHandler implements EventHandler {

    private int idControl;
    private Pane root;

    public SlideHandler(int idControl, Pane root) {
        this.idControl = idControl;
        this.root = root;
    }

    @Override
    public void handle(Event event) {
        // javafx calls handle methods in classes that are created and passed into a button's setOnAction method
        // See GUIBoxScene for implementation examples
        idControl++;
        GUIScene GuiScene = new GUIScene("Scene", idControl, root);
        GuiScene.setLayoutX(90);
        GuiScene.setLayoutY(140 + 50 * (root.getChildren().size() % 10));
        root.getChildren().add(GuiScene);
    }
}
