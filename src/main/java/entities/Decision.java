package entities;

import java.util.HashSet;
import java.util.Iterator;

/**
 * The decision entity that represents the decision components in the game
 * and GUI
 */
public class Decision {
    public String text;
    public Slide target;
    public Slide origin;
    public int id;
    private HashSet<Decision> conditionals;

    public Decision(String text) {
        this.text = text;
        this.conditionals = new HashSet();
    }

    public Decision(String text, Slide origin, int id, Slide target) {
        this.text = text;
        this.origin = origin;
        this.id = id;
        this.target = target;
        this.conditionals = new HashSet();
    }

    public Decision(String text, Slide origin, int id) {
        this.text = text;
        this.origin = origin;
        this.id = id;
        this.target = null;
        this.conditionals = new HashSet();
    }

    /**
     * Set attributes of the decision instance. Method names are
     * self-explanatory
     */
    public void setTarget(Slide target) {
        this.target = target;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() { return this.text; }

    public int getId() { return this.id; }

    public void setOrigin(Slide origin) { this.origin = origin; }

    public Slide getOrigin() { return this.origin; }

    public Slide getTarget() { return this.target; }

    public boolean checkConditionals(HashSet<Decision> checkAgainst) {
        if (!this.conditionals.isEmpty()) {
            Iterator conditionalsIterator = this.conditionals.iterator();
            while (conditionalsIterator.hasNext()) {
                if (!checkAgainst.contains(conditionalsIterator.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addToConditionals(Decision d) {
        this.conditionals.add(d);
    }
}
