package client.DisplayGame;
import entities.Game;
import entities.Player;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import entities.Slide;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static java.lang.Long.MAX_VALUE;

public class TitleScreen extends GameRenderer{

    public void displayFirstSlide(){
        VBox box = new VBox(105);
        box.setAlignment(Pos.CENTER);
        Label title =  new Label("Title Screen");
        title.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 55));
        Button start = createStartButton();
        Button settings = createSettingsButton();
        HBox hBox = new HBox(25);
        hBox.getChildren().addAll(start, settings);
        hBox.setAlignment(Pos.CENTER);

        box.getChildren().addAll(title, hBox);

        Scene scene=new Scene(box,1200,800);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
        stage.show();
    }

    public Button createStartButton(){
        Button button = createButton();
        button.setOnAction(arg0 -> player.playGame());
        button.setText("Start Game");
        return button;
    }

    private Button createSettingsButton(){
        Button button = createButton();
        button.setOnAction(arg0 -> System.out.println("menu"));
        button.setText("Settings");
        return button;
    }

    private Button createButton() {
        Button button = new Button();
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 25));
        button.setMaxWidth(MAX_VALUE);
        button.setPrefSize(180, 80);
        button.setCursor(Cursor.HAND);
        button.setTextFill(theme.textColor);
        button.setWrapText(true);
        button.setStyle("-fx-background-color: #ADD8E6; -fx-border-width: 2px;");
        return button;
    }

}
