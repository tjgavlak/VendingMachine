package com.techelevator.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy hh:mm:ss a");
    String dateText = localDateTime.format(formatter);
    LocalDateTime parseDateTime = localDateTime.parse(dateText, formatter);
    List<String> audit = new ArrayList<String>();
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
        audit.add(localDateTime.format(formatter) + product + " " + getSlot() + " " + String.format("%.2f", getPrice()) + " " + String.format("%.2f", currentTransaction.getBalance()));
        System.out.println("You selected " + product.getName() + ". " + "The cost is: " + product.getPrice() + ".");
            this.quantity -= 1;
    }

    public abstract void getMessage();
}
