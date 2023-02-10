package com.techelevator.view;

import java.math.BigDecimal;

public class ReturnChange {
    public static String returnChange (double currentTransaction) {
        Coin[] coins = new Coin[] {new Quarter(), new Dime(), new Nickel()};
//        currentTransaction *= 100;
        String result = "";

        for (Coin coin : coins) { //iterate through coin array
            if (currentTransaction == 0) {
                break;
            }
            double change = currentTransaction / coin.getValue();
            if (change > 0) {
                currentTransaction = currentTransaction % (coin.getValue() * change);

            }
        }
    }
}
