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
	private static final String FEED_QUIT = "Return to Main Menu";
	private static final String[] FEED_MENU_OPTIONS = {FEED_ONE, FEED_TWO, FEED_FIVE, FEED_QUIT};


	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private VendingMenu menu;

	public VendingMachineCLI(VendingMenu menu) throws FileNotFoundException {
		this.menu = menu;
	}

	VendingMachineChoices snackChoices = new VendingMachineChoices();
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
					//end();
					running = false;
					break;
			}
		}
		return;
	}

	public void displayItems() {
		System.out.println("Choices: " + snackChoices.getSnackChoices().size());
		for (String snack : snackChoices.getSnackChoices().keySet()) {
			String slot = snackChoices.getSnackChoices().get(snack).getSlot();
			String name = snackChoices.getSnackChoices().get(snack).getName();
			double price = snackChoices.getSnackChoices().get(snack).getPrice(); //TODO BigDecimal for money

			System.out.println(slot + " + " + name + " : " + price);

			if (snackChoices.getSnackChoices().get(snack).getQuantity() > 0) {
				System.out.println("Available: " + snackChoices.getSnackChoices().get(snack).getQuantity());
			} else {
				System.out.println("SOLD OUT");
			}
		}
	}

	public void purchaseItems() throws IOException {
		try (PrintWriter printToLog = new PrintWriter(logFile)) {
			System.out.println("Current balance: " + currentTransaction.getBalance());
			String purchaseMenuOption = String.valueOf(menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS));
			while (true) {
				if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
					while (true) {
						System.out.println("Current balance: " + currentTransaction.getBalance());
						String feedMoneyOptions = String.valueOf(menu.getChoiceFromOptions(FEED_MENU_OPTIONS));
						double add = 0;
						if (feedMoneyOptions.equals(FEED_ONE)) {
							currentTransaction.addMoney(1.00);
							add += 1.00;
						} else if (feedMoneyOptions.equals(FEED_TWO)) {
							currentTransaction.addMoney(2.00);
							add += 2.00;
						} else if (feedMoneyOptions.equals(FEED_FIVE)) {
							currentTransaction.addMoney(5.00);
							add += 5.00;
						} else if (feedMoneyOptions.equals(FEED_QUIT)) {
							System.out.println("Transaction aborted.");
							break;
						}
					}
					return;
				} else if (purchaseMenuOption.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
					for (String snack : snackChoices.getSnackChoices().keySet()) {
						System.out.println(" " + snackChoices.getSnackChoices().get(snack).getSlot() + " | ");
						System.out.println(" " + snackChoices.getSnackChoices().get(snack).getName() + " | ");
						System.out.println(" " + snackChoices.getSnackChoices().get(snack).getPrice() + " | ");
						if (snackChoices.getSnackChoices().get(snack).getQuantity() > 0) {
							System.out.println("Available: " + snackChoices.getSnackChoices().get(snack).getQuantity());
						} else {
							System.out.println("SOLD OUT");
						}
					}
				}
			}
		}
	}





	public static void main(String[] args) throws Exception {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
