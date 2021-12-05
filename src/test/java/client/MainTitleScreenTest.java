package client;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class MainTitleScreenTest {
        @Start
        public void start(Stage stage) throws Exception {
            launch(MainTitleScreen.class);
            stage.show();
        }

        @Test
        void should_contain_button_with_text() {
        }

}

