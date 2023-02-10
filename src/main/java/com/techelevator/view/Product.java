package com.techelevator.view;

import java.math.BigDecimal;

public interface Product {

    /*public String name = "";
    public String slot = "";
    public BigDecimal price = new BigDecimal(0.00);
    public int quantity = 5;
    public BigDecimal balance = new BigDecimal(0.00) ;*/

    public String getName();
    public BigDecimal getPrice();
    public BigDecimal getBalance();
    public String getSlot();
    public int getQuantity();
    public void purchaseItem();
    public String dispenseItem();
}
