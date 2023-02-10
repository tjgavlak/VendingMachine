package com.techelevator.view;

import java.math.BigDecimal;

public class CustomerMoney {
    private BigDecimal balance = new BigDecimal("0.00");

    public BigDecimal getBalance() {
        return balance;
    }

    public void addMoney(BigDecimal moneyInserted) {
        this.balance = (balance.add(moneyInserted));
    }

    public void subtractMoney(BigDecimal moneySubtracted) {
        if (this.balance.compareTo(moneySubtracted) >= 0) {
            this.balance = (balance.subtract(moneySubtracted)) ;
        }
    }
}
