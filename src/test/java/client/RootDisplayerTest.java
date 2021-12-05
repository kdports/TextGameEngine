package client;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;


public class RootDisplayerTest extends ApplicationTest {
    private final RootDisplayer rootDisplayer = new RootDisplayer();

    public void start(Stage stage) throws Exception {
        rootDisplayer.start(stage);
        stage.setScene(rootDisplayer.root.getScene());

        stage.show();
    }

    @Test
    void should_contain_button_with_text() {
    }

}