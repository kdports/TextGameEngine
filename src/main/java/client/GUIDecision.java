// package client;
//
// import entities.Slide;
// import entities.Studio;
// import javafx.geometry.Pos;
// import javafx.scene.control.Button;
// import javafx.scene.input.ClipboardContent;
// import javafx.scene.input.Dragboard;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.input.TransferMode;
// import javafx.scene.layout.Pane;
// import javafx.scene.layout.StackPane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
//
// import java.util.ArrayList;
//
// public class GUIDecision extends StackPane {
//     public String message = "Insert Dialogue...";
//     private GUISlide targetSlide;
//     private double mouseAnchorX;
//     private double mouseAnchorY;
//
//     //TODO: Create aesthetically pleasing decision rod
//     public GUIDecision(Pane root, int decisionIdControl){
//         this.setMaxSize(300, 5);
//         this.setMinSize(300, 5);
//         this.setStyle("-fx-background-color: #ee4540");
//
//         Button EditBtn = new Button("Edit Decision");
//         EditBtn.setStyle("-fx-background-color: #c72c41");
//         EditBtn.setOnMousePressed(mouseEvent -> {
//             DecisionAlertBox.display(root, this);
//         });
//
//         this.setId(((Double) Math.random()).toString());
//
//         Circle centerCircle = new Circle(5);
//         centerCircle.setFill(Color.GRAY);
//         centerCircle.setOnMousePressed(mouseEvent -> {
//             mouseAnchorX = mouseEvent.getX();
//             mouseAnchorY = mouseEvent.getY();
//         });
//
//         centerCircle.setOnMouseDragged(mouseEvent -> {
//             this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
//             this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
//         });
//
//         Circle rightConnection = new Circle(5);
//         rightConnection.setFill(Color.YELLOW);
//
//         rightConnection.setOnDragDetected((MouseEvent event) -> {
//
//             Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);
//
//             ClipboardContent content = new ClipboardContent();
//             content.putString("1," + decisionIdControl);
//             db.setContent(content);
//         });
//
//         Circle leftConnection = new Circle(5);
//         leftConnection.setFill(Color.YELLOW);
//
//         leftConnection.setOnDragDetected((MouseEvent event) -> {
//
//             Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);
//
//             ClipboardContent content = new ClipboardContent();
//             content.putString("0," + decisionIdControl);
//             db.setContent(content);
//         });
//
//         this.getChildren().addAll(leftConnection, EditBtn, rightConnection, centerCircle);
//         StackPane.setAlignment(leftConnection, Pos.BASELINE_LEFT);
//         StackPane.setAlignment(EditBtn, Pos.CENTER);
//         StackPane.setAlignment(rightConnection, Pos.TOP_RIGHT);
//         StackPane.setAlignment(centerCircle, Pos.BOTTOM_CENTER);
//
//     }
//
//     public void setTargetSlide(GUISlide targetSlide) {
//         this.targetSlide = targetSlide;
//     }
//
//     public Slide getTargetSlide() {
//         return this.targetSlide;
//     }
//
// //    public Slide findSecondScene(int targetId) {
// //        Studio studio = new Studio();
// //        ArrayList<Slide> slides = studio.getSlides();
// //        return slides.get(0); // Failsafe to appease java. Should always be at least one slide in existence
// //    }
// }
