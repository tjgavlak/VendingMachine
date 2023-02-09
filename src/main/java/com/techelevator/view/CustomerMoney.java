package com.techelevator.view;

public class CustomerMoney {
    private double balance = 0.0;

    public double getBalance() {
        return balance;
    }

    public void addMoney(double moneyInserted) {
        this.balance += moneyInserted;
    }

    public void subtractMoney(double moneySubtracted) {
        if (this.balance >= moneySubtracted) {
            this.balance -= moneySubtracted;
        }
    }
}
