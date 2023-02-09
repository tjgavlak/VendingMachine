package com.techelevator.view;

public class Quarter implements Coin{

    @Override
    public String getName() {
        return "Quarter";
    }

    @Override
    public int getValue() {
        return 25;
    }
}
