package se.kth.iv1350.amazingpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {
    private ItemRegistry instance;
    private String itemId = "apple";
    private int quantity = 1;
    private int taxRate = 1;
    private int price = 1;
    private ItemDescription itemDescription;

    @BeforeEach
    void setUp() {
        instance = new ItemRegistry();
        itemDescription = new ItemDescription(itemId, quantity, taxRate, price);
    }

    @AfterEach
    void tearDown() {
        instance = null;
        itemDescription = null;
    }

    @Test
    public void testFindItem() {
        String expResult = itemDescription.getItemId();
        String actualResult = instance.findItem(itemId).getItemId();
        assertEquals(expResult, actualResult,"Wrong itemIdentifier or this item is unavailable or invalid!");
    }
    @Test
    public void testNotFindItem() {
        String unavailableItemId = "car";
        ItemDescription expResult = null;
        ItemDescription actualResult = instance.findItem(unavailableItemId);
        assertEquals(expResult, actualResult,"this instance of itemDescription was found");
    }

}