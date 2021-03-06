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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    public Rectangle rounded;

    /**
     * Constructs an instance by setting the initial position, colour, and size of
     * the decision
     *
     * @param decision - The decision entity used for data.
     * @param guiSlide - The slide that this decision comes from.
     * @param x - The x location of the decision in the editor
     * @param y - The x location of the decision in the editor
     * @param theme - the theme of the gui
     */
    public GuiDecision(Decision decision, GuiSlide guiSlide, double x, double y, ThemeColours theme) {
        this.originSlide = guiSlide;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setMinWidth(120);
        this.setMaxWidth(120);
        this.setMinHeight(26);
        this.setMaxHeight(26);
        this.setId("GuiDecision");

        // Drag event handling
        this.initializeDragHandling();

        Button editButton = new EditDecisionButton(decision, theme);
        Circle leftConnection = new LeftDecisionConnectionPoint(decision, theme);
        Circle rightConnection = new RightDecisionConnectionPoint(decision, theme);

        // main slide
        rounded = new Rectangle();
        rounded.setWidth(105);
        rounded.setHeight(30);
        rounded.setArcHeight(15);
        rounded.setArcWidth(15);
        rounded.setStroke(Color.BLACK);

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
        this.setTheme(theme);

    }

    /**
     * Sets the theme of the decision and its elements
     *
     * @param theme - the ThemeColours instance containing the active theme
     */
    public void setTheme(ThemeColours theme){
        rounded.setFill(Color.valueOf(theme.active.slideColour));
        leftLine.setStroke(Color.valueOf(theme.active.textColour));
        rightLine.setStroke(Color.valueOf(theme.active.textColour));

        for (Node element : this.getChildren()){
            if (element instanceof Button) {
                element.setStyle("-fx-background-insets: 0;" +
                        "-fx-font-size: 10;"+
                        "-fx-background-color: " + theme.active.backgroundColour + ";" +
                        "-fx-text-fill: " + theme.active.textColour +";");
            }
            else if (element instanceof Circle) {
                ((Circle) element).setFill(Color.valueOf(theme.active.sidebarColour));
            }
        }
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

        input.setOnKeyReleased(mouseEvent -> Handlers.decisionHandler.editMessage(decision, input.getText()));
        // Edit the value on entry according to the value on the text field

        Button dltButton = new Button("Delete Decision");
        dltButton.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.deleteDecision(decision);
            slideWindow.close();
        });

        // Decision conditionals - a list of all decisions that aren't the one being edited
        ArrayList<Map.Entry<Decision, GuiDecision>> allDecisions = EditorGame.getTotalDecisions();
        ArrayList<Decision> possibleConditionals = new ArrayList<>();
        for (Map.Entry<Decision, GuiDecision> e : allDecisions) {
            Decision d = e.getKey();
            if (d != decision) {
                possibleConditionals.add(d);
            }
        }

        // Constructs an ArrayList of all decision's texts
        ArrayList<Text> allDecisionTexts = new ArrayList<>();
        for (Decision possibleDecision : possibleConditionals) {
            Text decisionText = new Text(possibleDecision.getText());
            boolean alreadyHas = Handlers.decisionHandler.hasDecisionConditional(decision, possibleDecision);
            if (alreadyHas) { decisionText.setUnderline(true); }
            else { decisionText.setUnderline(false); }
            allDecisionTexts.add(decisionText);
        }

        // A dropdown list of all decisions to select from
        ComboBox<Text> decisionComboBox = new ComboBox<>(FXCollections.observableArrayList(allDecisionTexts));
        decisionComboBox.setPromptText("Choose Decision Conditional");

        // When a decision is selected the actual decision object is gotten from its text and added
        decisionComboBox.setOnAction(mouseEvent -> {
            String chosenText = decisionComboBox.getValue().getText();
            boolean textChosen = false;
            for (Decision chosenDecision : possibleConditionals) {
                if (chosenText.equals(chosenDecision.getText()) && !textChosen) {
                    Handlers.decisionHandler.changeDecisionConditional(decision, chosenDecision);
                    textChosen = true;
                }
            }
        });

        TextField itemInput = new TextField(decision.getItemToGive());
        itemInput.setPromptText("Enter collected item...");
        itemInput.setOnKeyReleased(mouseEvent -> Handlers.decisionHandler.changeGivenItem(decision, itemInput.getText()));
        // Edit the value on entry according to the value on the text field

        // A hashset of all items given by other decisions
        HashSet<Text> allItems = new HashSet<>();
        for (Decision d : possibleConditionals) {
            if (d.getItemToGive() != null) {
                Text itemText = new Text(d.getItemToGive());
                if (decision.getItemConditionals().contains(d.getItemToGive())) { itemText.setUnderline(true); }
                else { itemText.setUnderline(false); }
                allItems.add(itemText);
            }
        }

        // A dropdown list of all decisions to select from
        ComboBox<Text> itemComboBox = new ComboBox<>(FXCollections.observableArrayList(allItems));
        itemComboBox.setPromptText("Choose Item Conditional");
        itemComboBox.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.changeItemConditional(decision, itemComboBox.getValue().getText());
        });

        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, dltButton, decisionComboBox,
                itemInput, itemComboBox);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
