package com.techelevator.view;

import java.math.BigDecimal;

public class Gum implements Product {
    private String name = "";
    private String slot = "";
    private Double price = 0.00;
    private int quantity = 5;
    private BigDecimal balance = new BigDecimal(0.00) ;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void purchaseItem() {
        if (this.quantity > 0) {
            this.quantity -= 1;
        }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String dispenseItem(String getName, String getPrice, BigDecimal getBalance) {
        String returnMessage = "";
        if (this.quantity == 0) {
            returnMessage += "This item is sold out.";
        } else {
            System.out.println("Chew Chew, Pop!");
            returnMessage += "You selected " + getName + ". " + "The cost is: " + getPrice + ". " + "Balance Remaining: " + getBalance;
            this.quantity -= 1;
        }
        return returnMessage;
    }
}