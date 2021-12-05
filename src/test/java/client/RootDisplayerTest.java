package client;

import client.GuiSlide.GuiSlide;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.robot.Robot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)

public class RootDisplayerTest {
    private final RootDisplayer rootDisplayer = new RootDisplayer();
    @Start
    public void start(Stage stage) throws Exception {
        rootDisplayer.start(stage);
        stage.setScene(rootDisplayer.root.getScene());

        stage.show();
    }

    @Test
    void try_opening_editor(FxRobot robot) {
        robot.clickOn("#button-add-slide");
        Assertions.assertThat(robot.lookup("#button-add-slide").queryAs(Button.class)).hasText("Add Slide");
//        robot.clickOn("#1").type(KeyCode.T);
        robot.clickOn(700, 500);
        robot.drag(670, 500).drag(600,300).drop();

        robot.clickOn("#button-add-slide");
        robot.drag(670, 500).drag(1400,700).drop();
        robot.clickOn("#button-change-theme");
        robot.clickOn("#theme-picker");
        robot.clickOn("Peachy");
        robot.clickOn(1025,505);
        robot.drag(1025, 505).drag(1400, 700).drop();
        robot.clickOn("SET FIRST SLIDE");
        robot.clickOn("Save");
        robot.closeCurrentWindow();
        robot.clickOn("Play Test");
        robot.clickOn("I am a new decision");
        robot.closeCurrentWindow();
        robot.closeCurrentWindow();
        robot.closeCurrentWindow(); // Should only need to close window once
        robot.clickOn("EDIT");
        robot.clickOn("Delete Decision");
        robot.clickOn(450, 230);
//        robot.clickOn(970, 505);
    }

}