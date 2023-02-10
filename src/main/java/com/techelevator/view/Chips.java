package com.techelevator.view;

public class Chips extends Product {

    @Override
    public void getMessage() {
        System.out.println("Glug Glug, Chug Chug!");
        System.out.println("Would you like something else? Y/N");
    }
}
