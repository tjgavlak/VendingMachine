package com.techelevator.view;

import java.math.BigDecimal;

public interface Product {

    public String getName();
    public Double getPrice();
    public BigDecimal getBalance();
    public String getSlot();
    public int getQuantity();
    public void purchaseItem();
    public String dispenseItem(String getName, String getPrice, BigDecimal getBalance);
}
