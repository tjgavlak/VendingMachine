package com.techelevator.view;

import java.math.BigDecimal;

abstract class Coin {
    String getName();
    int coinValue;

    public Coin(String coinType, int coinValue) {
        this.coinType = coinType;
        this.coinValue = coinValue;
    }


}
