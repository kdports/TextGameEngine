//package interfaces;
//
//import entities.Slide;
//import javafx.beans.property.SimpleDoubleProperty;
//
//public class RenderableSlide extends RenderableObject<Slide> {
//    private final SimpleDoubleProperty width = new SimpleDoubleProperty(200);
//    private final SimpleDoubleProperty height = new SimpleDoubleProperty(100);
//
//    public RenderableSlide(double windowWidth, double windowHeight) {
//        super(Math.random() * (windowWidth - 400) + 200, Math.random() * (windowHeight - 200) + 100, "box");
//    }
//
//    public double getWidth() {
//        return this.width.doubleValue();
//    }
//
//    public double getHeight() {
//        return this.height.doubleValue();
//    }
//
//    public void setHeight(double height) {
//        this.height.set(height);
//    }
//
//    public void setWidth(double width) {
//        this.width.set(width);
//    }
//}
