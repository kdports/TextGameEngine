package client;

import client.DisplayGame.GameRenderer;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)

public class TitleScreenTest {
    private final GameRenderer gameRenderer = new GameRenderer();

    @Start
    public void start(Stage stage) throws Exception {
        gameRenderer.start(stage);
        stage.show();
    }

    @Test
    void try_testing_settings(FxRobot robot) {
        robot.clickOn("Back");
        robot.clickOn("Launch Player");
        robot.closeCurrentWindow();
        robot.clickOn("Launch Player");
        robot.clickOn("Settings");
        robot.clickOn("Ocean");
        robot.clickOn("Peachy");
        robot.clickOn("Default");
        robot.closeCurrentWindow();
        robot.clickOn("Settings");
        Assertions.assertThat(robot.lookup("#button-Ocean").queryAs(Button.class)).isEnabled();
        Assertions.assertThat(robot.lookup("#button-Default").queryAs(Button.class)).isDisabled();
        robot.clickOn("Off");
        robot.clickOn("Fast");
        Assertions.assertThat(robot.lookup("#button-Fast").queryAs(Button.class)).isDisabled();
        robot.closeCurrentWindow();
        robot.clickOn("Settings");
        Assertions.assertThat(robot.lookup("#button-Default").queryAs(Button.class)).isDisabled();
        Assertions.assertThat(robot.lookup("#button-Fast").queryAs(Button.class)).isDisabled();
        robot.closeCurrentWindow();
        robot.closeCurrentWindow();
        robot.closeCurrentWindow();
    }

}

