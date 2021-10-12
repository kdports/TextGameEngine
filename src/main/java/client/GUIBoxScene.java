package main.java.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
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
                GUIScene GuiScene = new GUIScene("Scene", idControl, root);


                root.getChildren().add(GuiScene);


            }
        });

        root.getChildren().add(btn);
        Scene window = new Scene(root, 900, 900);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(window);
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
