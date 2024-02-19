package com.techelevator;

public class Gum extends Inventory {
    public Gum() {
    }

    public Gum(String slotLocation, String productName, double price, String type, int quantity) {
        super(slotLocation, productName, price, type, quantity);
    }

    public void sound() {
        System.out.println("\nChew Chew, Yum!");
    }
}
