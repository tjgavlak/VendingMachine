package com.techelevator.view;

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
