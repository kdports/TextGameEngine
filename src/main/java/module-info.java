module TextGameEngine.main {
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires org.apache.jena.core;

    opens client;
    opens handlers;
}