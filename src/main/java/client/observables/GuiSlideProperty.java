// package client.observables;
//
// import client.GuiSlide.GuiSlide;
// import javafx.beans.InvalidationListener;
// import javafx.beans.property.Property;
// import javafx.beans.value.ChangeListener;
// import javafx.beans.value.ObservableValue;
//
// public class GuiSlideProperty implements Property<GuiSlide> {
//     private GuiSlide value;
//     public GuiSlideProperty(GuiSlide guiSlide) {
//         this.value = guiSlide;
//     }
//
//     @Override
//     public GuiSlide getValue() {
//         return value;
//     }
//
//     @Override
//     public void setValue(GuiSlide value) {
//         this.value = value;
//     }
//
//     @Override
//     public void bind(ObservableValue<? extends GuiSlide> observable) {
//         observable.bind(this);
//     }
//
//     @Override
//     public void unbind() {
//
//     }
//
//     @Override
//     public boolean isBound() {
//         return false;
//     }
//
//     @Override
//     public void bindBidirectional(Property<GuiSlide> other) {
//         this.bind(other);
//         other.bind(this);
//     }
//
//     @Override
//     public void unbindBidirectional(Property<GuiSlide> other) {
//
//     }
//
//     @Override
//     public Object getBean() {
//         return null;
//     }
//
//     @Override
//     public String getName() {
//         return null;
//     }
//
//     @Override
//     public void addListener(InvalidationListener listener) {
//
//     }
//
//     @Override
//     public void removeListener(InvalidationListener listener) {
//
//     }
// }
