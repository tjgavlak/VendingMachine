package com.techelevator.view;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ReturnChange {
    public static String returnChange(BigDecimal currentTransaction) {
        Coin[] coins = new Coin[]{new Quarter(), new Dime(), new Nickel()};
        BigDecimal zeroBalance = new BigDecimal("0.00");
        String result = "";
        Map<Coin, Double> changeMap = new HashMap<Coin, Double>();

        for (Coin coin : coins) { //iterate through coin array
//            double change = currentTransaction / coin.getValue();
            if (currentTransaction.equals(zeroBalance)) {
                break;
            }
            double change = Double.parseDouble(String.valueOf(currentTransaction));
            change = change / Double.parseDouble(String.valueOf(coin.getValue()));
            if (change > 0) {
                currentTransaction = currentTransaction.remainder(coin.getValue().multiply(BigDecimal.valueOf(change)));
                changeMap.put(coin, change);
            }
        }
//        System.out.println("Your change is: ");
        for (Coin coin : changeMap.keySet()) {

           result = (changeMap.get(coin) + ": " + coin.getName() + "(s) ");
        }
        return result;

    }
}
