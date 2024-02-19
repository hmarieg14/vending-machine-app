package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String SECRET = "Sales Report";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
            MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, SECRET};

    private static Inventory inventory;
    private static ProductMenu productMenu;
    private static Money money;
    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        inventory = new Inventory();
        productMenu = new ProductMenu();
        money = new Money();
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

    public void run() {
        inventory.initializeInventory("vendingMachine.csv");
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                inventory.displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                productMenu.purchaseMenu();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("\nThank You For Shopping!");
                break;
            } else if (choice.equals(SECRET)) {
                money.salesReport();
                System.out.println("\nSales Report Created");
                break;
            }
        }
    }
}
