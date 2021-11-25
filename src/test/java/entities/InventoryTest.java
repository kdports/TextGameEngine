package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testItems() {
        Inventory i = new Inventory();
        String key = "key";
        assertTrue(i.getItems().isEmpty());

        i.addItem(key);
        assertTrue(i.getItems().contains("key"));

        i.addItem(key);
        assertTrue(i.getItems().size() == 1);
    }
}