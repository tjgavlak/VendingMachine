package com.techelevator.view;

import java.math.BigDecimal;

public class Nickel implements Coin{

    @Override
    public String getName() {
        return "Nickel";

    }

    @Override
    public int getValue() {
        return 5;
    }
}
