package entities;
import java.util.HashSet;

public class Inventory {
    private HashSet<String> items;

    public Inventory() {
        this.items = new HashSet<String>();
    }

    public HashSet<String> getItems() { return this.items; }

    public void addItem(String item) { this.items.add(item); }

    public void removeItem(String item) { this.items.remove(item); }

    public void clearItems() { this.items.clear(); }
}
