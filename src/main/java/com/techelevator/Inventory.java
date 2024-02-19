package com.techelevator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;


public class Inventory {
    public static TreeMap<String, Inventory> products;
    private String slotLocation;
    private String productName;
    private double price;
    private String type;
    private int quantity;


    public Inventory() {
        products = new TreeMap<>();
    }

    public Inventory(String slotLocation, String productName, double price, String type,
                     int quantity) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void initializeInventory(String filePath) {
        //reads file to use for an array
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String slotLocation = parts[0];
                    String productName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String type = parts[3];
                    int quantity = getQuantity();
                    //creates a usable array from the vendingmachine.csv file
                    Chip chip = new Chip(slotLocation, productName, price, type, quantity);
                    if (parts[3].equals("Chip")) {
                        products.put(chip.getSlotLocation(), chip);
                    }
                    Candy candy = new Candy(slotLocation, productName, price, type, quantity);
                    if (parts[3].equals("Candy")) {
                        products.put(candy.getSlotLocation(), candy);
                    }
                    Drink drink = new Drink(slotLocation, productName, price, type, quantity);
                    if (parts[3].equals("Drink")) {
                        products.put(drink.getSlotLocation(), drink);
                    }
                    Gum gum = new Gum(slotLocation, productName, price, type, quantity);
                    if (parts[3].equals("Gum")) {
                        products.put(gum.getSlotLocation(), gum);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inventory file: " + e.getMessage());
        }
    }


    public void displayItems() {
        for (Inventory product : products.values()) {
            String availability = product.getQuantity() > 0 ?
                    Integer.toString(product.getQuantity()) : "SOLD OUT";
            //only displays name and quantity
            System.out.println(product.getProductName() + " | " + availability);
        }

    }

    public void displayAllItemDetails() {
        for (Inventory product : products.values()) {
            String availability = product.getQuantity() > 0 ?
                    Integer.toString(product.getQuantity()) : "SOLD OUT";
            String actualPrice = String.format("%.2f", product.getPrice());
            //displays all the details of the item
            System.out.println(product.getSlotLocation() + " | " + product.getProductName() + " " + "|" + " " + "$" + actualPrice + " | " + product.getType() + " | " + availability);
        }
    }

    public boolean isProductAvailable(String slotLocation) {
        Inventory product = products.get(slotLocation);
        return product != null && product.getQuantity() > 0;
        //checks if product is available
    }

    public Inventory getProduct(String slotLocation) {
        return products.get(slotLocation);
        //method used to get a specific slotLocation
    }


}
