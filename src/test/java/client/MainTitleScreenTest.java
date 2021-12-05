package client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.stage.Stage;

import static javafx.application.Application.launch;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)

public class MainTitleScreenTest {
        private final MainTitleScreen mainTitleScreen = new MainTitleScreen();
        @Start
        public void start(Stage stage) throws Exception {
            mainTitleScreen.start(stage);

            stage.show();
        }

        @Test
        void try_opening_editor(FxRobot robot) {
            robot.clickOn("Launch Creator Studio");
            robot.closeCurrentWindow();
            robot.clickOn("Launch Player");
            robot.clickOn("Back");
            robot.clickOn("Quit");

        }

}

