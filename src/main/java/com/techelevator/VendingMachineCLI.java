package com.techelevator;

import com.techelevator.view.*;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

    private static final String FEED_ONE = "Add $1.00";
    private static final String FEED_TWO = "Add $2.00";
    private static final String FEED_FIVE = "Add $5.00";
    private static final String FEED_QUIT = "Return to Purchase Menu";
    private static final String[] FEED_MENU_OPTIONS = {FEED_ONE, FEED_TWO, FEED_FIVE, FEED_QUIT};

    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private VendingMenu menu;

    public VendingMachineCLI(VendingMenu menu) throws FileNotFoundException {
        this.menu = menu;
    }

    VendingMachineChoices snackChoices = new VendingMachineChoices();
    Map<String, Product> inventory = snackChoices.getMapOfProduct();
    CustomerMoney currentTransaction = new CustomerMoney();
    ReturnChange returnChange = new ReturnChange();
    TransactionLog logWriter = new TransactionLog();


    public void run() throws IOException {
        boolean running = true;
        while (running) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            // A switch statement could also be used here.  Your choice.
            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    displayItems();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    purchaseItems();
                    break;
                // display vending machine items
                case MAIN_MENU_OPTION_EXIT:
                    running = false;
                    break;
            }
        }
    }

    // Runs when user chooses to display items from main menu
    public void displayItems() {
        System.out.println("Choices: ");
        for (String snack : inventory.keySet()) {
            String slot = inventory.get(snack).getSlot();
            String name = inventory.get(snack).getName();
            BigDecimal price = inventory.get(snack).getPrice();

            System.out.println(slot + " | " + name + " | " + price);

            if (inventory.get(snack).getQuantity() > 0) {
                System.out.println("Available: " + inventory.get(snack).getQuantity());
            } else {
                System.out.println("SOLD OUT");
            }
        }
    }

    // Runs when user chooses to make purchase
    public void purchaseItems() throws IOException {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Current balance: " + currentTransaction.getBalance());
            String purchaseMenuOption = String.valueOf(menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS));
            boolean isCustomer = true;
            boolean isFeeding = true;
            while (isCustomer) {
                // User choice to feed money, select product, or finish transaction
                if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                    while (isFeeding) {
                        System.out.println("Current balance: " + currentTransaction.getBalance());
                        String feedMoneyOptions = String.valueOf(menu.getChoiceFromOptions(FEED_MENU_OPTIONS));
                        double add = 0;
                        switch (feedMoneyOptions) {
                            case FEED_ONE:
                                currentTransaction.addMoney(BigDecimal.valueOf(1.00));
                                add += 1.00;
                                break;
                            case FEED_TWO:
                                currentTransaction.addMoney(BigDecimal.valueOf(2.00));
                                add += 2.00;
                                break;
                            case FEED_FIVE:
                                currentTransaction.addMoney(BigDecimal.valueOf(5.00));
                                add += 5.00;
                                break;
                            case FEED_QUIT: // User is done adding money, ready to purchase
                                isFeeding = false;
                                purchaseItems();
                                break;
                        }
                        String feedMoneyLog = "FEED MONEY: " + "$" + add + " " + "$" + currentTransaction.getBalance();
                        logWriter.log(feedMoneyLog);
                    }
                    // If user choice is Select Product
                } else if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                    // If user balance is 0
                    if (currentTransaction.getBalance().compareTo(BigDecimal.valueOf(0.00)) < 0) {
                        System.out.println("Please make a deposit.");
                        break;
                    } else { // If balance is greater than 0
                            displayItems();
                            System.out.println("Current Balance: " + currentTransaction.getBalance());
                            System.out.println("Please enter item code: ");
                            String itemChoice = userInput.nextLine().toUpperCase();
                            Product product = inventory.get(itemChoice);
                            try {
                                if (currentTransaction.getBalance().compareTo(product.getPrice()) < 0) {
                                    System.out.println("Insufficient funds.");
                                    break;
                                } else if (inventory.get(itemChoice).getQuantity() > 0) {
                                    currentTransaction.subtractMoney(inventory.get(itemChoice).getPrice()); // Takes their money
                                    product.dispenseItem(product);
                                    product.getMessage();
                                    purchaseItems();
                                }
                        } catch (NullPointerException e) {
                                System.out.println("Please enter a valid item code.");
                                purchaseItems();
                            }
                    }
                } else if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                    String change = ReturnChange.returnChange(currentTransaction.getBalance());
                    System.out.println("Your change is: " + change);
                    String changeLog = "GIVE CHANGE: " +  "$" + currentTransaction.getBalance() + " $0.00"; // ¯\_(ツ)_/¯
                    currentTransaction.subtractMoney(currentTransaction.getBalance());
                    logWriter.log(changeLog);
                }
                isCustomer = false;
            }
    }

    public static void main(String[] args) throws Exception {
        VendingMenu menu = new VendingMenu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
