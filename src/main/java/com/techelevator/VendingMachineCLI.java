package com.techelevator;

import com.techelevator.view.CustomerMoney;
import com.techelevator.view.Product;
import com.techelevator.view.VendingMachineChoices;
import com.techelevator.view.VendingMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


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
	//private static final String FEED_QUIT = "Return to Purchase Menu";
	private static final String[] FEED_MENU_OPTIONS = {FEED_ONE, FEED_TWO, FEED_FIVE, PURCHASE_MENU_OPTION_SELECT_PRODUCT};


	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
	private static final String[] PRODUCT_OPTIONS = {};

	private VendingMenu menu;

	public VendingMachineCLI(VendingMenu menu) throws FileNotFoundException {
		this.menu = menu;
	}

	VendingMachineChoices snackChoices = new VendingMachineChoices();
	Map<String, Product> inventory = snackChoices.getMapOfProduct();
	CustomerMoney currentTransaction = new CustomerMoney();

	File logFile = new File("log.txt"); // TODO consider making a logger class to handle this
	Scanner scanner = new Scanner(System.in);

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
			double price = inventory.get(snack).getPrice(); //TODO BigDecimal for money

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
		try (PrintWriter printToLog = new PrintWriter(logFile)) {
			System.out.println("Current balance: " + currentTransaction.getBalance());
			String purchaseMenuOption = String.valueOf(menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS));
			boolean isCustomer = true;
			while (isCustomer) {
				// User choice to feed money, select product, or finish transaction
				if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					while (isCustomer) {
						System.out.println("Current balance: " + currentTransaction.getBalance());
						String feedMoneyOptions = String.valueOf(menu.getChoiceFromOptions(FEED_MENU_OPTIONS));
						double add = 0;
						switch (feedMoneyOptions) {
							case FEED_ONE:
								currentTransaction.addMoney(1.00);
								add += 1.00;
								break;
							case FEED_TWO:
								currentTransaction.addMoney(2.00);
								add += 2.00;
								break;
							case FEED_FIVE:
								currentTransaction.addMoney(5.00);
								add += 5.00;
								break;
							case PURCHASE_MENU_OPTION_SELECT_PRODUCT: // User is done adding money, ready to purchase
								System.out.println("Choices: ");
								for (String snack : inventory.keySet()) {
									String slot = inventory.get(snack).getSlot();
									String name = inventory.get(snack).getName();
									double price = inventory.get(snack).getPrice(); //TODO BigDecimal for money

									System.out.println(slot + " | " + name + " | " + price);

									if (inventory.get(snack).getQuantity() > 0) {
										System.out.println("Available: " + inventory.get(snack).getQuantity());
									} else {
										System.out.println("SOLD OUT");
									}
								}
								System.out.println("Please enter an item number.");
								String itemChoice = userInput.nextLine().toUpperCase();
								break;
						}
					}
					isCustomer = false;

				// If user choice is Select Product
				} else if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					// If user balance is 0
					if (currentTransaction.getBalance() <= 0) {
						System.out.println("Please give us money.");
						//purchaseMenuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY);
					} else { // If balance is greater than 0
						System.out.println("Choices: ");
						for (String snack : inventory.keySet()) {
							String slot = inventory.get(snack).getSlot();
							String name = inventory.get(snack).getName();
							double price = inventory.get(snack).getPrice(); //TODO BigDecimal for money

							System.out.println(slot + " | " + name + " | " + price);

							if (inventory.get(snack).getQuantity() > 0) {
								System.out.println("Available: " + inventory.get(snack).getQuantity());
							} else {
								System.out.println("SOLD OUT");
							}
						}
					}
				}
			}
		}
	}

		public static void main (String[]args) throws Exception {
			VendingMenu menu = new VendingMenu(System.in, System.out);
			VendingMachineCLI cli = new VendingMachineCLI(menu);
			cli.run();
		}
	}
