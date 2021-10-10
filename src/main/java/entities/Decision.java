package main.java.entities;

public class Decision {
    public String text;
    public Slide target;
    
    public Decision(String text) {
        this.text = text;
    }

    public Decision(String text, Slide target) {
        this.text = text;
        this.target = target;
    }

    public void setTarget(Slide target) {
        this.target = target;
    }
}
