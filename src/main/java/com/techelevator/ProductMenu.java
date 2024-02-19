package com.techelevator;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductMenu {
    private final Inventory inventory = new Inventory();
    private final NumberFormat currency = NumberFormat.getCurrencyInstance();
    public Money money = new Money();
    private Chip chip = new Chip();
    private Gum gum = new Gum();
    private Drink drink = new Drink();
    private Candy candy = new Candy();

    public void promptCode() {
        inventory.displayAllItemDetails(); Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter product code: "); String userCode = scanner.nextLine();
        // Put in an ignoreCase for userCode input
        Inventory selectedProduct = inventory.getProduct(userCode);
        if (inventory.isProductAvailable(userCode)) {
            if (inventory.getProduct(userCode).getQuantity() > 0) {
                if (money.getAmountOfMoney() < selectedProduct.getPrice()) {
                    System.err.println("Insufficient funds");
                    // if user's money is not enough the program prints this out
                } else {
                    Inventory i = Inventory.products.get(userCode);
                    i.setQuantity((i.getQuantity() - 1));
                    Inventory.products.put(userCode, i);
                    dispenseProduct(selectedProduct);
                }
            } else {
                System.out.println("Inventory is SOLD OUT");
            }
        } else {
            System.out.println("Code does not exist, please enter a valid code");
        }
    }

    public void dispenseProduct(Inventory product) {
        switch (product.getType()) {
            case "Chip":
                chip.sound(); //prints out the item's "sound"
                money.itemLog(product.getProductName(), product.getSlotLocation(), product.getPrice()
                        , money.amountOfMoney);
                //logs transaction to Log.txt file
                break;
            case "Candy":
                candy.sound();
                money.itemLog(product.getProductName(), product.getSlotLocation(), product.getPrice()
                        , money.amountOfMoney);
                break;
            case "Drink":
                drink.sound();
                money.itemLog(product.getProductName(), product.getSlotLocation(), product.getPrice()
                        , money.amountOfMoney);
                break;
            case "Gum":
                gum.sound();
                money.itemLog(product.getProductName(), product.getSlotLocation(), product.getPrice()
                        , money.amountOfMoney);
                break;
        }
        // Update balance from remaining money and return the user to the purchase menu
        money.setAmountOfMoney(money.getAmountOfMoney() - product.getPrice());
        System.out.println("\nRemaining Balance: " + currency.format(money.getAmountOfMoney()));
    }

    public void purchaseMenu() {

            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            while (true) {
                System.out.println("\nCurrent Money Provided: " + currency.format(money.amountOfMoney));
                System.out.println("\n1) Feed Money");
                System.out.println("2) Select Product");
                System.out.println("3) Complete Transaction");
                System.out.print("\nPlease choose an option >>> ");
                try{
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println(" ");
                if (choice == 3) {
                    money.finishTransaction();
                    //logs transaction and returns change
                    running = false;
                    break;
                }
                switch (choice) {
                    case 1:
                        // made subtle change here.
                        money.feedMoney();
                        break;
                    case 2:
                        promptCode();
                        //opens up the inventory again and allows user to input item code
                        break;
                    default:
                        System.out.println("\nPlease select a valid choice");
                }
            } catch (InputMismatchException e) {
                    System.err.print("Please provide valid input\n");
                    System.out.println("\n");
                }
        }
    }
}