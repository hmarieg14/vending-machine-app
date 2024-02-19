package com.techelevator;

public class Chip extends Inventory{
    public Chip() {
    }

    public Chip(String slotLocation, String productName, double price, String type, int quantity){
        super(slotLocation, productName, price, type, quantity);

    }
    public void sound(){
        System.out.println("\nCrunch Crunch, Yum!");
    }

}
