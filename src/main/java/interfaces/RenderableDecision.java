package interfaces;

import entities.Decision;

public class RenderableDecision extends RenderableObject<Decision>{
    public RenderableDecision(double windowWidth, double windowHeight) {
        super(windowWidth / 2, windowHeight / 2, "line");
    }
}
