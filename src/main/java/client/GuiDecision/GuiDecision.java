package client.GuiDecision;

import client.GuiDecision.DecisionConnectionPoint.LeftDecisionConnectionPoint;
import client.GuiDecision.DecisionConnectionPoint.RightDecisionConnectionPoint;
import client.GuiSlide.GuiSlide;
import client.ThemeColours;
import entities.Decision;
import entities.EditorGame;
import handlers.Handlers;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * The object representing the decisions displayed inside the editor.
 */
public class GuiDecision extends StackPane {
    private static EditorGame editorGame;
    private double mouseAnchorX;
    private double mouseAnchorY;
    public DecisionLine leftLine;
    public DecisionLine rightLine;
    public GuiSlide originSlide;
    public GuiSlide targetSlide;
    public double sceneX;
    public double sceneY;

    /**
     * Constructs an instance by setting the initial position, colour, and size of
     * the decision
     *
     * @param decision - The decision entity used for data.
     * @param guiSlide - The slide that this decision comes from.
     * @param x - The x location of the decision in the editor
     * @param y - The x location of the decision in the editor
     */
    public GuiDecision(Decision decision, GuiSlide guiSlide, double x, double y, ThemeColours theme) {
        this.originSlide = guiSlide;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setMinWidth(120);
        this.setMaxWidth(120);
        this.setMinHeight(26);
        this.setMaxHeight(26);

        // Drag event handling
        this.initializeDragHandling();

        Button editButton = new EditDecisionButton(decision, theme);
        Circle leftConnection = new LeftDecisionConnectionPoint(decision, theme);
        Circle rightConnection = new RightDecisionConnectionPoint(decision, theme);

        // main slide
        Rectangle rounded = new Rectangle();
        rounded.setWidth(105);
        rounded.setHeight(30);
        rounded.setArcHeight(15);
        rounded.setArcWidth(15);
        rounded.setStroke(Color.BLACK);
        rounded.setFill(Color.valueOf(theme.active.slideColour));

        this.getChildren().addAll(rounded, editButton,rightConnection,leftConnection);

        // Initializing the two DecisionLine's
        leftLine = new DecisionLine(
                this.getLayoutX(),
                this.getLayoutY(),
                this.getLayoutX(),
                this.getLayoutY() + 13,
                ConnectionDirection.ORIGIN,
                originSlide
        );
        rightLine = new DecisionLine(
                this.getLayoutX() + 100,
                this.getLayoutY() + 25,
                this.getLayoutX() + 100,
                this.getLayoutY() + 25,
                ConnectionDirection.TARGET,
                targetSlide
        );

        leftLine.setStroke(Color.valueOf(theme.active.textColour));
        rightLine.setStroke(Color.valueOf(theme.active.textColour));

//         targetSlide.addListener(event -> {
//             rightLine.setSlide(this.targetSlide.getValue());
//         });
    }

    /**
     * Constructor for testing/debugging purposes only
     */
    public GuiDecision(String s) {
    }

    /**
     * Create the event handlers to deal with drag events.
     */
    private void initializeDragHandling() {
        this.setOnMousePressed(event -> {
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();

        });
        this.setOnMouseDragged(event -> {
            this.setLayoutX(sceneX + event.getSceneX() - mouseAnchorX);
            this.setLayoutY(sceneY + event.getSceneY() - mouseAnchorY);
            leftLine.setEndX(sceneX + event.getSceneX() - mouseAnchorX);
            leftLine.setEndY(sceneY + event.getSceneY() - mouseAnchorY  + 13);
            rightLine.setStartX(sceneX + event.getSceneX() - mouseAnchorX + 120);
            rightLine.setStartY(sceneY + event.getSceneY() - mouseAnchorY + 13);
        });
    }

    /**
     * Show the dialog box that allows the user to change the text on a decision.
     *
     * @param decision - The decision to change the text of.
     */
    static void showEdit(Decision decision) {
        // Create an Alert Box to edit the Decision
        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Decision Editor");
        slideWindow.setMinWidth(300);
        TextField input = new TextField(decision.getText());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> slideWindow.close());

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the Decision message");
        btnEdit.setOnAction(mouseEvent -> Handlers.decisionHandler.editMessage(decision, input.getText()));

        Button dltButton = new Button("Delete Decision");
        dltButton.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.deleteDecision(decision);
            slideWindow.close();
        });

        // Decision conditionals - a list of all decisions that aren't the one being edited
        ArrayList<Map.Entry<Decision, GuiDecision>> allDecisions = editorGame.getTotalDecisions();
        ArrayList<Decision> possibleConditionals = new ArrayList<Decision>();
        for (Map.Entry<Decision, GuiDecision> e : allDecisions) {
            Decision d = e.getKey();
            if (d != decision) {
                possibleConditionals.add(d);
            }
        }
        Text decisionCondition = new Text("Choose decision conditionals below");
        // A dropdown list of all decisions to select from
        ComboBox<Decision> decisionComboBox = new ComboBox<Decision>(FXCollections.observableArrayList(possibleConditionals));
        decisionComboBox.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.changeDecisionConditional(decision, decisionComboBox.getValue());
        });

        TextField itemInput = new TextField(decision.getItemToGive());
        // Edit the value on entry according to the value on the text field
        Button btnItemEdit = new Button("Save the above text as an item to give");
        btnItemEdit.setOnAction(mouseEvent -> Handlers.decisionHandler.changeGivenItem(decision, itemInput.getText()));

        // A hashset of all items given by other decisions
        HashSet<String> allItems = new HashSet<String>();
        for (Decision d : possibleConditionals) {
            if (d.getItemToGive() != null) {
                allItems.add(d.getItemToGive());
            }
        }
        Text itemCondition = new Text("Choose item conditionals below");
        // A dropdown list of all decisions to select from
        ComboBox<String> itemComboBox = new ComboBox<String>(FXCollections.observableArrayList(allItems));
        itemComboBox.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.changeItemConditional(decision, itemComboBox.getValue());
        });

        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, btnEdit, btnClose, dltButton, decisionCondition, decisionComboBox,
                itemInput, btnItemEdit, itemCondition, itemComboBox);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
