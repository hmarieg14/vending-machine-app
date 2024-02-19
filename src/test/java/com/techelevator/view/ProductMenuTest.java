package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.ProductMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMenuTest {

    private ProductMenu productMenu;

    @BeforeEach
    public void init() {
        productMenu = new ProductMenu();
    }

    // Test dispenseProduct() when product is chip
    @Test
    public void testDispenseProduct_whenProductIsChip_shouldDecreaseBalance() {
        Inventory product = new Inventory("Potato Crisps", "A1", 3.05, "Chip", 5); // create a
        // chip product
        double initialBalance = 5.0; // set an initial balance
        productMenu.money.setAmountOfMoney(initialBalance); productMenu.dispenseProduct(product);
        double expectedBalance = initialBalance - product.getPrice();
        double actualBalance = productMenu.money.getAmountOfMoney();
        assertEquals(expectedBalance, actualBalance, "Balance should decrease by product price " +
                "after dispensing");
    }

    @Test
    public void testDispenseProduct_whenProductIsChip_shouldDecreaseBalance_v2() {
        Inventory product = new Inventory("Potato Crisps", "A1", 2.00, "Chip", 5);
        double initialBalance = 5.0; // set an initial balance
        productMenu.money.setAmountOfMoney(initialBalance); productMenu.dispenseProduct(product);
        double expectedBalance = initialBalance - product.getPrice();
        double actualBalance = productMenu.money.getAmountOfMoney();
        assertEquals(expectedBalance, actualBalance, "Balance should decrease by product price " +
                "after dispensing");
    }
}
