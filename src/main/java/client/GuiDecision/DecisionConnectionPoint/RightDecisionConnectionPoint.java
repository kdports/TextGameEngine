package client.GuiDecision.DecisionConnectionPoint;

import entities.Decision;
import javafx.geometry.Pos;

/**
 * A DecisionConnectionPoint parameterized for the right side of a connection.
 */
public class RightDecisionConnectionPoint extends DecisionConnectionPoint {
    public RightDecisionConnectionPoint(Decision decision) {
        super(decision);
    }

    @Override
    protected String decideBeginningCharacter() { return "1"; }

    @Override
    protected Pos decidePosition() { return Pos.CENTER_RIGHT; }
}
