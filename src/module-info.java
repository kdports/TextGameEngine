module TextGameEngine.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens main.java.client;
}