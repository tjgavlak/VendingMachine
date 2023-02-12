package com.techelevator.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReturnChange {

    public static String returnChange(BigDecimal currentTransaction) {
        double balance = currentTransaction.doubleValue();
        balance *= 100;
        Coin[] coins = new Coin[]{new Quarter(), new Dime(), new Nickel()};
        String result = "";
        Map<Coin, Integer> changeMap = new LinkedHashMap<>();
        for (Coin coin : coins) { //iterate through coin array
            if (balance == 0.00) {
                result = "";
                break;
            }
            int change = (int)balance / coin.getValue();
            if (change > 0) {
                balance %= coin.getValue() * change;
                changeMap.put(coin, change);
            }
        }
        for (Coin coin : changeMap.keySet()) {
           result += (changeMap.get(coin) + " " + coin.getName() + "(s) ");
        }
            return result;

    }

}
