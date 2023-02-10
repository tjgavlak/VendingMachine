package com.techelevator.view;

import java.math.BigDecimal;

public abstract class Product {

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
        System.out.println("You selected " + product.getName() + ". " + "The cost is: " + product.getPrice() + ".");
            this.quantity -= 1;
    }

    public abstract void getMessage();
}
