module TextGameEngine.main {
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires org.apache.jena.core;

    opens client;
    opens handlers;
    opens client.GuiSlide;
    opens client.GuiDecision;
    opens client.GuiDecision.DecisionConnectionPoint;
}