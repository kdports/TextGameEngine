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
    private HashSet<Decision> decisionConditionals;
    private HashSet<String> itemConditionals;
    public String itemToGive;

    public Decision(String text) {
        this.text = text;
        this.decisionConditionals = new HashSet();
        this.itemConditionals = new HashSet<>();
    }

    public Decision(String text, Slide origin, int id, Slide target) {
        this.text = text;
        this.origin = origin;
        this.id = id;
        this.target = target;
        this.decisionConditionals = new HashSet();
        this.itemConditionals = new HashSet<>();
    }

    public Decision(String text, Slide origin, int id) {
        this.text = text;
        this.origin = origin;
        this.id = id;
        this.target = null;
        this.decisionConditionals = new HashSet();
        this.itemConditionals = new HashSet<String>();
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

    public HashSet<Decision> getDecisionConditionals() {
        return decisionConditionals;
    }

    public HashSet<String> getItemConditionals() {
        return itemConditionals;
    }

    public void setItemToGive(String s) { this.itemToGive = s; }

    public String getItemToGive() { return this.itemToGive; }

    public boolean hasItemToGive() { return this.itemToGive != null; }


    /**
     * Certifies that every decision in the decisionConditionals HashSet is also in the inputted set
     * @param checkAgainst A given set, normally player.pastChosenDecisions
     * @return Whether decisionConditionals is empty or all in the given HashSet
     */
    public boolean checkConditionals(HashSet<Decision> checkAgainst, HashSet<String> itemsHeld) {
        if (!this.decisionConditionals.isEmpty()) {
            Iterator conditionalsIterator = this.decisionConditionals.iterator();
            while (conditionalsIterator.hasNext()) {
                if (!checkAgainst.contains(conditionalsIterator.next())) {
                    return false;
                }
            }
        }
        if (!this.itemConditionals.isEmpty()) {
            Iterator conditionalsIterator = this.itemConditionals.iterator();
            while (conditionalsIterator.hasNext()) {
                if (!itemsHeld.contains(conditionalsIterator.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Adds the given decision to this decision's conditionals
     * @param d The decision to add to conditionals
     */
    public void addToDecisionConditionals(Decision d) {
        this.decisionConditionals.add(d);
    }

    /**
     * If decision d is already in conditionals it is removed. Otherwise it is added.
     * @param d The decision to either remove or add
     */
    public void switchDecisionConditional(Decision d) {
        if (this.decisionConditionals.contains(d)) {
            this.decisionConditionals.remove(d);
        }
        else {
            addToDecisionConditionals(d);
        }
    }

    /**
     * Adds the given item to this decision's conditionals
     * @param s The item to add to the conditionals
     */
    public void addToItemConditionals(String s) {
        this.itemConditionals.add(s);
    }

    /**
     * If item d is already in conditionals it is removed. Otherwise it is added.
     * @param s The item to remove or add
     */
    public void switchItemConditional(String s) {
        if (this.itemConditionals.contains(s)) {
            this.itemConditionals.remove(s);
        }
        else {
            addToItemConditionals(s);
        }
    }
}
