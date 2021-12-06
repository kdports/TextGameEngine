package client;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import javafx.geometry.Bounds;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.VerticalDirection;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

import java.util.Set;

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
        Assertions.assertThat(robot.lookup("#button-add-slide").queryAs(Button.class)).hasText("Add Slide");
        robot.clickOn("#button-add-slide");
        robot.clickOn(robot.from(robot.lookup("#1").queryAs(GuiSlide.class)).lookup("#add-decision-button").queryAs(Button.class));
        robot.drag(robot.lookup("#1").queryAs(GuiSlide.class).getLayoutX() + 35,robot.lookup("#1").queryAs(GuiSlide.class).getLayoutY()).drag(600,300).drop();
        robot.clickOn("#button-add-slide");

        robot.clickOn("#button-change-theme");
        robot.clickOn("#theme-picker");
        robot.clickOn("Peachy");
        robot.drag(robot.from(robot.lookup("#GuiDecision").queryAs(GuiDecision.class)).
                lookup("#right-decision-connector").queryAs(Circle.class)).dropTo("#3");
        robot.clickOn("SET FIRST SLIDE");
        robot.clickOn("Save");
        robot.closeCurrentWindow();

        robot.clickOn("EDIT");
        robot.clickOn("Enter collected item...").write("Cool Item");
        robot.clickOn("I am a new decision").write(" Test");
        robot.closeCurrentWindow();
        robot.clickOn("Play Test");
        robot.clickOn("Themes");
        robot.clickOn("Peachy");
        robot.clickOn("Animation");
        robot.clickOn("Fast");
        robot.closeCurrentWindow();
        robot.clickOn(robot.from(robot.lookup("#1").queryAs(GuiSlide.class)).lookup("#add-decision-button").queryAs(Button.class));
        robot.drag(robot.lookup("#GuiDecision").queryAs(GuiDecision.class).getLayoutX() +70,robot.lookup("#1").queryAs(GuiSlide.class).getLayoutY() + 10).dropTo(1500, 200);
        robot.scroll(20, VerticalDirection.DOWN);
        robot.scroll(20, VerticalDirection.UP);
        robot.clickOn("EDIT");
        robot.clickOn("Choose Decision Conditional");
        robot.clickOn("I am a new decision");
        robot.clickOn("Delete Decision");
        robot.clickOn("EDIT");
        robot.clickOn("Choose Item Conditional");
        robot.clickOn("Cool Item");;
        robot.closeCurrentWindow();
        robot.clickOn(robot.from(robot.lookup("#1").queryAs(GuiSlide.class)).lookup("#delete-slide").queryAs(Button.class));
        robot.clickOn("Load");
    }

}