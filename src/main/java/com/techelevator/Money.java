package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Money {
    private final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    public double amountOfMoney;

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void feedMoney() {
        Scanner moneyScanner = new Scanner(System.in);
        System.out.print("\nInsert the amount of money: ");
        double dollars = moneyScanner.nextDouble();
        double currentBalance = amountOfMoney += dollars;
        //adds the amount inputted to the current money provided
        logTransaction("FEED MONEY", dollars, currentBalance);
        //logs the money that was fed to the machine alone with the current balance
    }

    public void finishTransaction() {
        // Calculate and return change
        double change = returnChange(amountOfMoney);
        System.out.println("\n\nTotal Change Returned: " + currency.format(change));
        // Reset the amount of money to zero
        amountOfMoney = 0.0;
        System.out.println("\nTransaction complete. Thank you for your purchase.");
    }

    public double returnChange(double amountOfMoney) {
        double change = amountOfMoney;
        try (FileWriter writer = new FileWriter("Log.txt", true)) {
            Date now = new Date();
            String formattedDate = dateFormat.format(now);
            int quarters = (int) (change / 0.25);
            change %= 0.25;
            int dimes = (int) (change / 0.10);
            change %= 0.10;
            int nickels = (int) (change / 0.05);
            //returns change in coins
            System.out.println("\nCoins returned: " + "\nQuarters: " + quarters + "\nDimes: " + dimes + "\nNickels: " + nickels);

            writer.write(formattedDate + " GIVE CHANGE: " + currency.format(amountOfMoney) + " " + currency.format(0.00) + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to Log.txt: " + e.getMessage());
        }
        return amountOfMoney;
    }

    public void logTransaction(String action, double amountOfMoney, double currentBalance) {
        try (FileWriter writer = new FileWriter("Log.txt", true)) {
            Date now = new Date();
            String formattedDate = dateFormat.format(now);
            //logs transaction to Log.txt file
            writer.write(formattedDate + " " + action + ": " + currency.format(amountOfMoney) +
                    " " + currency.format(currentBalance) + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to Log.txt: " + e.getMessage());
        }
    }

    public void itemLog(String name, String slot, double amountOfMoney, double currentBalance) {
        try (FileWriter writer = new FileWriter("Log.txt", true)) {
            Date now = new Date();
            String formattedDate = dateFormat.format(now);
            //logs each item transaction
            writer.write(formattedDate + " " + name + " " + slot + " " + currency.format(amountOfMoney) + " " + currency.format(currentBalance) + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to Log.txt: " + e.getMessage());
        }
    }

    public void salesReport() {
        // Generate a unique file name for the sales report based on the current date and time
        SimpleDateFormat fileDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date now = new Date();
        String fileName = "SalesReport_" + fileDateFormat.format(now) + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            //double totalSales = 0.0;

            for (Inventory product : Inventory.products.values()) {
                int sales = 5 - product.getQuantity(); //this is subtracting the base amount of
                // stock each product begins with by the actual quantity
                //totalSales += sales * product.getPrice();
                writer.write(product.getProductName() + "|" + sales + "\n");
            }

            writer.write("\nTOTAL SALES " + currency.format(totalSales()));
        } catch (IOException e) {
            System.err.println("Error writing to sales report: " + e.getMessage());
        }
    }
    public double totalSales(){
        //Created for unit testing purposes
        double totalSales = 0.0;
        for(Inventory product : Inventory.products.values()){
            int sales = 5 - product.getQuantity();
            totalSales += sales * product.getPrice();
        }
        return totalSales;
    }
}
