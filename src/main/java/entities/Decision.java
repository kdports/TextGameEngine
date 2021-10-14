package entities;

public class Decision {
    public String text;
    public Slide target;
    
    public Decision(String text) {
        this.text = text;
    }

    public void addTarget(Slide target) {
        this.target = target;
    }
}
