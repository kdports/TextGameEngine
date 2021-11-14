package client.GuiDecision.DecisionConnectionPoint;

import entities.Decision;
import javafx.geometry.Pos;

/**
 * A DecisionConnectionPoint parameterized for the left side of a connection.
 */
public class LeftDecisionConnectionPoint extends DecisionConnectionPoint {
    public LeftDecisionConnectionPoint(Decision decision) {
        super(decision);
    }

    @Override
    protected String decideBeginningCharacter() { return "0"; }

    @Override
    protected Pos decidePosition() { return Pos.CENTER_LEFT; }
}
