package com.techelevator.view;

import java.math.BigDecimal;

public class Candy implements Product {
    /*Candy moonpie =  new Candy();
    Candy cowtales = new Candy();
    Candy wonkaBar = new Candy();
    Candy crunchie = new Candy();*/
    public String name = "";
    public String slot = "";
    public BigDecimal price = new BigDecimal(0.00);
    public int quantity = 5;
    public BigDecimal balance = new BigDecimal(0.00) ;


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
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public String dispenseItem(String getName, BigDecimal getPrice, BigDecimal getBalance) {
        String returnMessage = "";
        if (this.quantity == 0) {
            returnMessage += "This item is sold out.";
        } else {
            System.out.println("Munch Munch, Mmm Mmm Good!");
            returnMessage += "You selected " + getName + ". " + "The cost is: " + getPrice + ". " + "Balance Remaining: " + getBalance;
            this.quantity -= 1;
        }
        return returnMessage;
    }
}
