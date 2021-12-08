package entities;
import java.util.HashSet;

public class Inventory {
    /**
     * An inventory is a hashset of strings that is contained within a Player
     */
    private HashSet<String> items;

    public Inventory() {
        this.items = new HashSet<String>();
    }

    public HashSet<String> getItems() { return this.items; }

    public void addItem(String item) { this.items.add(item); }

    // The following two functions are currently unused but remain here for future extension.
    // While they are not called in the scope of this course they would be invaluable if this project is maintained
    public void removeItem(String item) { this.items.remove(item); }

    public void clearItems() { this.items.clear(); }
}
