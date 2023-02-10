package com.techelevator.view;

import java.math.BigDecimal;

public class Dime implements Coin {

    @Override
    public String getName() {
        return "Dime";
    }

    @Override
    public int getValue() {
        return 10;
    }
}
