package com.techelevator;

public class Drink extends Inventory {
    public Drink() {
    }

    public Drink(String slotLocation, String productName, double price, String type, int quantity) {
        super(slotLocation, productName, price, type, quantity);
    }

    public void sound(){
        System.out.println("\nGlug Glug, Yum!");
    }
}
