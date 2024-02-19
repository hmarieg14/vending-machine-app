package com.techelevator;

public class Candy extends Inventory {
    public Candy() {
    }

    public Candy(String slotLocation, String productName, double price, String type, int quantity) {
        super(slotLocation, productName, price, type, quantity);
    }

    public void sound() {
        System.out.println("\nMunch Munch, Yum!");
    }
}
