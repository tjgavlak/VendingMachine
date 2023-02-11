package com.techelevator.view;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class ReturnChange {
    public static String returnChange(BigDecimal currentTransaction) {
        Coin[] coins = new Coin[]{new Quarter(), new Dime(), new Nickel()};
        BigDecimal zeroBalance = new BigDecimal("0.00");
        String result = "";
        Map<Coin, BigDecimal> changeMap = new HashMap<Coin, BigDecimal>();
        for (Coin coin : coins) { //iterate through coin array
//            double change = currentTransaction / coin.getValue();
            if (currentTransaction.equals(zeroBalance)) {
                result = "0.00";
                break;
            }
            BigDecimal change = currentTransaction.divide(coin.getValue(), 0, RoundingMode.DOWN);
            if (change.compareTo(BigDecimal.ZERO) > 0 && change.compareTo(new BigDecimal("200")) < 0) {
                currentTransaction = currentTransaction.remainder(coin.getValue().multiply(change));
                changeMap.put(coin, change);
            } /*else if (change.compareTo(new BigDecimal("200")) > 0) {
                result = result + "Go fuck yourself.";
            }*/
        }
//        System.out.println("Your change is: ");
        for (Coin coin : changeMap.keySet()) {

           result = (changeMap.get(coin) + " " + coin.getName() + "(s) ");
        }
            return result;

    }

//    public static String returnChange (BigDecimal currentTransaction) {
//        Coin[] coins = new Coin[] {new Quarter(), new Dime(), new Nickel()};
//        BigDecimal zeroBalance = new BigDecimal("0.00");
//        int quarters = 0;
//        int dimes = 0;
//        int nickels = 0;
//
//        while (currentTransaction.compareTo(zeroBalance) > 0) { //iterate through coin array
//
//            BigDecimal change = currentTransaction;
//            if (change.remainder(new BigDecimal(25)).compareTo(BigDecimal.ZERO) == 0) {
//                quarters++;
//               // change -= 25;
//            } else if (change.remainder(new BigDecimal(10)).compareTo(BigDecimal.ZERO) == 0) {
//                dimes++;
//               // change -= 10;
//            } else {
//                nickels++;
//               // change -= 5;
//            }
//        }
//
//        String result = "Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + "nickels.";
//        return result;
//    }
}
