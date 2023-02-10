package com.techelevator.view;

import java.math.BigDecimal;

public class ReturnChange {
    public static String returnChange (double currentTransaction) {
        Coin[] coins = new Coin[] {new Quarter(), new Dime(), new Nickel()};
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        currentTransaction *= 100;

        for (Coin coin : coins) { //iterate through coin array
            double change = currentTransaction / coin.getValue();
            if (currentTransaction == 0) {
                break;
            } else if (change % 25 == 0) {
                quarters++;
                change -= 25;
            } else if (change % 10 == 0) {
                dimes++;
                change -= 10;
            } else {
                nickels++;
                change -= 5;
            }
        }

        String result = "Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + "nickels.";
        return result;
    }
}
