package main.java.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class GUIBoxScene extends Application
{
    int idControl = 0;
    private double mouseAnchorX;
    private double mouseAnchorY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = new Pane();
        Button btn = new Button();
        btn.setText("Add New Button");
        btn.setId("Button " + idControl);

        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                idControl++;
                StackPane stack = new StackPane();
                stack.setId("Decision" + idControl);
                stack.setMaxSize(300, 100);
                stack.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");



                Rectangle rect = new Rectangle(300, 100);
                rect.setOnMousePressed(mouseEvent -> {
                    mouseAnchorX = mouseEvent.getX();
                    mouseAnchorY = mouseEvent.getY();
                });

                rect.setOnMouseDragged(mouseEvent -> {
                    stack.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
                    stack.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
                });

                Button b1 = new Button("Create Decision");
                Button b2 = new Button("Delete Scene");
                stack.getChildren().addAll(rect, b1, b2);
                StackPane.setAlignment(b1, Pos.TOP_RIGHT);
                StackPane.setAlignment(b2, Pos.BOTTOM_RIGHT);
                b2.setId("Decision" + idControl);
                b2.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        root.getChildren().remove(stack);
                    }

                });
                root.getChildren().add(stack);


            }
        });
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
