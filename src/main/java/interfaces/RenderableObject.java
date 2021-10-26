package interfaces;

abstract public class RenderableObject<T> {
    private double x;
    private double y;
    private double anchorX;
    private double anchorY;
    String type;

    public RenderableObject(double x, double y, String type) {
        this.x = x;
        this.y = y;
        this.anchorX = x;
        this.anchorY = y;
        this.type = type;
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
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
