package interfaces;

import entities.Slide;

public class RenderableSlide extends RenderableObject<Slide> {
    private double width = 200;
    private double height = 100;
    public RenderableSlide(double windowWidth, double windowHeight) {
        super(Math.random() * (windowWidth - 400) + 200, Math.random() * (windowHeight - 200) + 100, "box");

    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
