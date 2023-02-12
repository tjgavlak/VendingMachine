package com.techelevator.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    TransactionLog logWriter = new TransactionLog();
    CustomerMoney currentTransaction = new CustomerMoney();

    private String name;
    private BigDecimal price;
    private String slot;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void purchaseItem(){
        if (this.quantity > 0) {
            this.quantity -= 1;
        }
    }

    public void dispenseItem(Product product) {
        String dispenseLog = getName() + " " + getSlot() + " " + "$" + getPrice() + " " + "$" + currentTransaction.getBalance();
        logWriter.log(dispenseLog);
        System.out.println("You selected " + product.getName() + ". " + "The cost is: " + product.getPrice() + ".");
            this.quantity -= 1;
    }

    public abstract void getMessage();
}
