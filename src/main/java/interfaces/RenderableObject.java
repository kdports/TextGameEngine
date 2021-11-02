package interfaces;

import javafx.beans.property.SimpleDoubleProperty;

abstract public class RenderableObject<T> {
    private final SimpleDoubleProperty x;
    private final SimpleDoubleProperty y;
    private double anchorX;
    private double anchorY;
    String type;

    public RenderableObject(double x, double y, String type) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);

        this.anchorX = x;
        this.anchorY = y;
        this.type = type;
    }

    public SimpleDoubleProperty getXProperty() {
        return this.x;
    }

    public SimpleDoubleProperty getYProperty() {
        return this.y;
    }

    public double getAnchorX() {
        return anchorX;
    }

    public double getAnchorY() {
        return anchorY;
    }

    public void setAnchorX(double anchorX) {
        this.anchorX = anchorX;
    }

    public void setAnchorY(double anchorY) {
        this.anchorY = anchorY;
    }

    public double getX() {
        return this.x.doubleValue();
    }

    public double getY() {
        return this.y.doubleValue();
    }

    public void changeX(double x) {
        this.x.set(x);
    }

    public void changeY(double y) {
        this.y.set(y);
    }
}
