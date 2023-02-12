package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineChoices {

    private Map<String, Product> snackChoices = new LinkedHashMap<>();

    public Map<String, Product> getMapOfProduct() throws FileNotFoundException {
        String fileName = "vendingmachine.csv";

        File vendingMachineInventory = new File(fileName);

        try (Scanner fileScanner = new Scanner(vendingMachineInventory)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splitLine = line.split("\\|");

                switch (splitLine[0].charAt(0)) {
                    case 'A':
                        Chips chips = new Chips();
                        chips.setSlot(splitLine[0]);
                        chips.setName(splitLine[1]);
                        chips.setPrice(BigDecimal.valueOf(Double.parseDouble(splitLine[2])));
                        chips.setQuantity(5);
                        snackChoices.put(splitLine[0], chips);
                        break;
                    case 'B':
                        Candy candy = new Candy();
                        candy.setSlot(splitLine[0]);
                        candy.setName(splitLine[1]);
                        candy.setPrice(BigDecimal.valueOf(Double.parseDouble(splitLine[2])));
                        candy.setQuantity(5);
                        snackChoices.put(splitLine[0], candy);
                        break;
                    case 'C':
                        Beverages beverages = new Beverages();
                        beverages.setSlot(splitLine[0]);
                        beverages.setName(splitLine[1]);
                        beverages.setPrice(BigDecimal.valueOf(Double.parseDouble(splitLine[2])));
                        beverages.setQuantity(5);
                        snackChoices.put(splitLine[0], beverages);
                        break;
                    case 'D':
                        Gum gum = new Gum();
                        gum.setSlot(splitLine[0]);
                        gum.setName(splitLine[1]);
                        gum.setPrice(BigDecimal.valueOf(Double.parseDouble(splitLine[2])));
                        gum.setQuantity(5);
                        snackChoices.put(splitLine[0], gum);
                        break;
                }
            }
        }
        return snackChoices;
    }
}
