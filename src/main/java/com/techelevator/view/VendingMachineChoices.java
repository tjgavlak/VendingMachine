package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineChoices {

    Map<String, Product> snackChoices;

    public VendingMachineChoices() {
        snackChoices = new LinkedHashMap<>();
        getMapOfProduct();
    }

    public Map<String, Product> getSnackChoices() {
        return snackChoices;
    }

    public Map<String, Product> getMapOfProduct() {
        String fileName = "";
        fileName = "vendingmachine.csv";

        File vendingMachineInventory = new File(fileName);

        try (Scanner fileScanner = new Scanner(vendingMachineInventory)) {
            while (fileScanner.hasNextLine());
            String line = fileScanner.nextLine();
            String[] splitLine = line.split("\\|");

            switch (splitLine[0].charAt(0)) {
                case 'A':
                    Chips chips = new Chips();
                    chips.setSlot(splitLine[0]);
                    chips.setName(splitLine[1]);
                    chips.setPrice(Double.parseDouble(splitLine[2]));
                    snackChoices.put(splitLine[0], chips);
                    break;
                case 'B':
                    Candy candy = new Candy();
                    candy.setSlot(splitLine[0]);
                    candy.setName(splitLine[1]);
                    candy.setPrice(Double.parseDouble(splitLine[2]));
                    snackChoices.put(splitLine[0], candy);
                    break;
                case 'C':
                    Beverages beverages = new Beverages();
                    beverages.setSlot(splitLine[0]);
                    beverages.setName(splitLine[1]);
                    beverages.setPrice(Double.parseDouble(splitLine[2]));
                    snackChoices.put(splitLine[0], beverages);
                    break;
                case 'D':
                    Gum gum = new Gum();
                    gum.setSlot(splitLine[0]);
                    gum.setName(splitLine[1]);
                    gum.setPrice(Double.parseDouble(splitLine[2]));
                    snackChoices.put(splitLine[0], gum);
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return snackChoices;
    }
}
