package com.techelevator.models;

import com.techelevator.UI.UserInput;
import com.techelevator.models.products.Product;

public class Purchase {
    private static double moneyProvided = 0;
    private double totalCost = 0;

    public static double getMoneyProvided() {
        return moneyProvided;
    }

    public Purchase() {
        this.moneyProvided = moneyProvided;
    }

    public double feedMoney(double money) {
        moneyProvided += money;
        return moneyProvided;
    }

    public double selectionPrice(Inventory inventory) {

        String item = UserInput.getItemSelection();
        double price = 0;

        for (Product product : inventory.getProducts()) {

            if(item.equals(product.getRowId())){
                price = product.getPrice();
                totalCost += price;
            }
        }
        return totalCost;
    }

    public double transaction() {

        double changeDue = 0;

        if (moneyProvided >= totalCost) {
            changeDue = moneyProvided - totalCost;
        } else if (moneyProvided < totalCost) {
            //insert insufficient funds exception
        }
        return changeDue;
    }

}
