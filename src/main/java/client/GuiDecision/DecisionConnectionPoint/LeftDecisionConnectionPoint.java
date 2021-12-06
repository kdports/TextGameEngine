package client.GuiDecision.DecisionConnectionPoint;

import client.ThemeColours;
import entities.Decision;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

/**
 * A DecisionConnectionPoint parameterized for the left side of a connection.
 */
public class LeftDecisionConnectionPoint extends DecisionConnectionPoint {
    public LeftDecisionConnectionPoint(Decision decision, ThemeColours theme) {
        super(decision);
        this.setFill(Color.valueOf(theme.active.sidebarColour));
        this.setId("left-decision-connector");
    }

    @Override
    protected String decideBeginningCharacter() { return "0"; }

    @Override
    protected Pos decidePosition() { return Pos.CENTER_LEFT; }
}
