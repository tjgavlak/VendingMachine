package com.techelevator.view;

public class Candy extends Product {

    @Override
    public void getMessage() {
        System.out.println("Munch Munch, Mmm Mmm Good!");
        System.out.println("Would you like something else? Y/N");
    }
}
