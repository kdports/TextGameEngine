package client;

import entities.Slide;
import entities.Studio;
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
        idControl++;
        GUIScene GuiScene = new GUIScene("Scene", idControl, root);
        Studio studio = new Studio();
        studio.addSlide(new Slide("Insert Dialogue..."));
        GuiScene.setLayoutX(90);
        GuiScene.setLayoutY(140 + 50 * (root.getChildren().size() % 10));
        root.getChildren().add(GuiScene);
    }
}
