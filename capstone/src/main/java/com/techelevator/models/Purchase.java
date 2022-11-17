package com.techelevator.models;

import com.techelevator.models.products.Product;

public class Purchase {
    private static double moneyAvailable = 0;
    private double totalCost = 0;

    public static void setMoneyAvailable(double moneyAvailable) {
        Purchase.moneyAvailable = moneyAvailable;
    }

    public static double getMoneyAvailable() {
        return moneyAvailable;
    }

    public Purchase() {
        this.moneyAvailable = moneyAvailable;
    }

    public double feedMoney(double money) {
        moneyAvailable += money;
        return moneyAvailable;
    }

    public double makeSelection(Inventory inventory, String item) {
        double price = 0;

        for (Product product : inventory.getProducts()) {

            if(item.equals(product.getRowId())){
                price = product.getPrice();
                totalCost += price;
                product.setQuantity(product.getQuantity() - 1);
            }
        }
        return totalCost;
    }

    public double transaction() {
        if (moneyAvailable >= totalCost) {
            moneyAvailable -= totalCost;
        } else if (moneyAvailable < totalCost) {
            //insert insufficient funds exception
        }
        return moneyAvailable;
    }

}
