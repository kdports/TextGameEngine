// package client;
//
// import handlers.CreateNewDecisionHandler;
// import handlers.CreateNewSlideHandler;
// import handlers.DragDropOnSourceHandler;
// import handlers.DragDropOnTargetHandler;
// import interfaces.EditDisplayer;
// import entities.Studio;
// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.canvas.Canvas;
// import javafx.scene.control.Button;
// import javafx.stage.Stage;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.Pane;
//
//
// import java.io.IOException;
//
// public class RootDisplayer extends Application implements EditDisplayer {
//     int idControl = 0;
//     Pane root = new Pane();
//     private double mouseAnchorX;
//     private double mouseAnchorY;
//     private Studio studio;
//     private SaveHandler saveHandler;
//     private final CreateNewSlideHandler createNewSlideHandler = new CreateNewSlideHandler(this, this.studio, 1920, 1080);
//     private final CreateNewDecisionHandler createNewDecisionHandler = new CreateNewDecisionHandler(this, this.studio, idControl);
//     private final DragDropOnSourceHandler dragDropOnSourceHandler = new DragDropOnSourceHandler(this, this.studio, idControl);
//     private final DragDropOnTargetHandler dragDropOnTargetHandler = new DragDropOnTargetHandler(this, this.studio, idControl);
//
//     public void setStudio(Studio studio) {
//         this.studio = studio;
//     }
//
//     public void start(String[] args) {
//         launch(args);
//     }
//
//     @Override
//     public void start(Stage primaryStage) throws IOException {
//         StackPane holder = new StackPane();
//         Canvas canvas = new Canvas(5000,  5000);
//         holder.getChildren().add(canvas);
//         root.getChildren().add(holder);
//
//         //min dimensions of the window
//         primaryStage.setMinHeight(400);
//         primaryStage.setMinWidth(500);
//
//         this.addButtons(root);
//
//         Scene window = new Scene(root, 1920, 1080);
//         //DECIDES BACKGROUND COLOUR
//         holder.setStyle("-fx-background-color: #2d142c");
//         primaryStage.setTitle("Text Studio");
//         primaryStage.setScene(window);
//         primaryStage.show();
//     }
//
//
//
//     @Override
//     public void createSlide() {
//
//     }
//
//     @Override
//     public void createDecision() {
//         idControl++;
//         GUIDecision GuiDecision = new GUIDecision(root, idControl);
//         GuiDecision.setLayoutX(root.getWidth() / 2);
//         GuiDecision.setLayoutY(root.getHeight() / 2);
//
//         root.getChildren().add(GuiDecision);
//     }
// }
