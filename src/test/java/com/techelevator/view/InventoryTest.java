package com.techelevator.view;

import com.techelevator.Inventory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory();
    }

    @Test
    public void testGetSlotLocation() {
        inventory.setSlotLocation("A1"); assertEquals("A1", inventory.getSlotLocation());
    }

    @Test
    public void testGetProductName() {
        inventory.setProductName("Candy"); assertEquals("Candy", inventory.getProductName());
    }

    @Test
    public void testGetPrice() {
        inventory.setPrice(1.0); assertEquals(1.0, inventory.getPrice(), 0.001);
    }

    @Test
    public void testGetType() {
        inventory.setType("Candy"); assertEquals("Candy", inventory.getType());
    }

    @Test
    public void testGetQuantity() {
        inventory.setQuantity(5); assertEquals(5, inventory.getQuantity());
    }
}
