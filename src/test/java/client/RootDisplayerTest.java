package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import javafx.stage.Stage;
import org.testfx.api.FxToolkit;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;

public class RootDisplayerTest extends ApplicationTest {
    @Before
    public void setUpClass() throws Exception{
        launch(RootDisplayer.class);


    }

    @Override
    public void start(Stage stage) throws Exception {
        launch(RootDisplayer.class);
        stage.show();
    }

    @Test
    public void should_contain_buttons() {
        // rightClickOn("#button-add-slide");
        // verifyThat("#button-add-slide", (Button add_slide) -> add_slide.getAccessibleText().contains("Add Slide"));

    }
}