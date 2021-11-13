// package client;
//
// import entities.Slide;
// import interfaces.EditDisplayer;
// import javafx.event.ActionEvent;
// import javafx.event.Event;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;
// import javafx.scene.input.DragEvent;
// import javafx.scene.input.Dragboard;
// import javafx.scene.input.TransferMode;
// import javafx.scene.layout.Background;
// import javafx.scene.layout.BackgroundFill;
// import javafx.scene.layout.Pane;
// import javafx.scene.layout.StackPane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Rectangle;
//
// public class GUISlide extends StackPane {
//     int idControl;
//     private double mouseAnchorX;
//     private double mouseAnchorY;
//
//     public GUISlide(String s, int idControl, Pane root, EventHandler<Event> createNewDecisionHandler) {
//         this.idControl = idControl;
//         this.setMaxSize(300, 100);
//         this.setStyle("-fx-background-color: #510a32;-fx-border-color: #801336;");
//         this.setId(s + idControl);
//
//         //TODO: Add slide number to slide
//
//         //Rectangle code
//         Rectangle rect = new Rectangle(300, 100);
//         rect.setFill(Color.web("510a32"));
//         rect.setOnMousePressed(mouseEvent -> {
//             mouseAnchorX = mouseEvent.getX();
//             mouseAnchorY = mouseEvent.getY();
//         });
//
//
//         rect.setOnMouseDragged(mouseEvent -> {
//             this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
//             this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
//         });
//
//         //text box code
//         TextArea dialogue = new TextArea();
//         dialogue.setWrapText(true);
//
//         // TextField dialogue = new TextField();
//         dialogue.setStyle("-fx-background-color: #801336;" + "-fx-blend-mode: overlay");
//         dialogue.setMaxWidth(130);
//         dialogue.setPromptText("Enter Dialogue...");
//         StackPane.setAlignment(dialogue, Pos.CENTER);
//
//         // Button Code
//         // TODO: Decide better method for applying colours. Perhaps CSS Styling
//         Button addDecisionButton = this.addAddDecisionButton(createNewDecisionHandler);
//         Button deleteSlideButton = this.addDeleteSlideButton(root);
//
//         this.getChildren().addAll(rect, addDecisionButton, deleteSlideButton, dialogue);
//
//         this.setOnDragOver(new EventHandler<DragEvent>() {
//             public void handle(DragEvent event) {
//                 if (event.getGestureSource() != this && event.getDragboard().hasString()) {
//                     event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                 }
//
//                 event.consume();
//             }
//         });
//
//         this.setOnDragDropped((DragEvent event) -> {
//             Dragboard db = event.getDragboard();
//             if (db.hasString()) {
//                 System.out.println("Dropped: " + db.getString());
//                 StringBuilder desiredDecision = new StringBuilder(db.getString());
//                 desiredDecision.deleteCharAt(0);
//                 String outputDecision = desiredDecision.toString();
//                 if (Character.toString(db.getString().charAt(0)).equals("0")){
//                      GUIDecision initialDecision = (GUIDecision) root.lookup("#" + outputDecision);
//                      initialDecision.initialScene = this;
//                 } else {
//                     GUIDecision initialDecision = (GUIDecision) root.lookup("#" + outputDecision);
//                     initialDecision.setTargetSlide(this);
//                 }
//                 event.setDropCompleted(true);
//             } else {
//                 event.setDropCompleted(false);
//             }
//             event.consume();
//         });
//     }
//
//     private Button addAddDecisionButton(EventHandler<Event> handler) {
//         Button addDecisionButton = new Button("+Decision");
//         addDecisionButton.setStyle("-fx-background-color: #c72c41; ");
//         StackPane.setAlignment(addDecisionButton, Pos.CENTER_RIGHT);
//
//         addDecisionButton.setOnMouseClicked(handler);
//
//         return addDecisionButton;
//     }
//
//     private Button addDeleteSlideButton(Pane root) {
//         Button deleteSlideButton = new Button("X");
//         deleteSlideButton.setStyle("-fx-background-color: #c72c41; ");
//         StackPane.setAlignment(deleteSlideButton, Pos.TOP_LEFT);
//
//         deleteSlideButton.setOnMousePressed(mouseEvent -> {
//             root.getChildren().remove(this);
//         });
//
//         return deleteSlideButton;
//     }
//
//     public int findId() {
//         return this.idControl;
//     }
// }
