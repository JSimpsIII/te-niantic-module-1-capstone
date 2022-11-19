package com.techelevator.models;

import com.techelevator.models.file_io.Logger;
import com.techelevator.models.products.Product;

public class Purchase {
    private static double moneyAvailable = 0;
    private double totalCost = 0;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

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

                if (product.getProductType().equals("Chip")) {
                    System.out.println("Crunch Crunch, Yum!");
                } else if (product.getProductType().equals("Candy")) {
                    System.out.println("Munch Munch, Yum!");
                } else if (product.getProductType().equals("Drink")) {
                    System.out.println("Glug Glug, Yum!");
                } else if (product.getProductType().equals("Gum")) {
                    System.out.println("Chew Chew, Yum!");
                }

                System.out.println("You have purchased " + product.getName() + " for $" + product.getPrice() + ".");
                Logger.logMessage(product.getName() + " " + product.getRowId() + " $" + product.getPrice()
                            + " $" + (getMoneyAvailable() - product.getPrice()) );
            }
        }
        return totalCost;
    }

    public double transaction() {
        if (moneyAvailable >= totalCost) {
            moneyAvailable -= totalCost;
            totalCost = 0;
        } else if (moneyAvailable < totalCost) {
            //insert insufficient funds exception
        }
        System.out.println("Your remaining balance is $" + getMoneyAvailable());
        return moneyAvailable;

    }

    public String change() {

        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;

        int changeDue = (int)(getMoneyAvailable()*100);
        int modQuarters = changeDue % QUARTER;
        int modDimes = modQuarters % DIME;
        int modNickels = modQuarters % NICKEL;

        int numQuarters = (changeDue - modQuarters) / QUARTER;
        int numDimes = (modQuarters - modDimes) / DIME;
        int numNickels = (modDimes - modNickels) / NICKEL;

//        System.out.println("\nTotal amount of change to give: $" + changeDue);
//        System.out.println("Number of quarters to give: " + numQuarters);
//        System.out.println("Number of dimes to give: " + numDimes);
//        System.out.println("Number of nickels to give: " + numNickels);
//        System.out.println("Number of pennies to give: " + numPennies);

        moneyAvailable = 0;

        double displayChangeDue = (double) changeDue / 100;

        return "\nTotal amount of change to give: $" + displayChangeDue + "\nNumber of quarters to give: " + numQuarters +
                "\nNumber of dimes to give: " + numDimes + "\nNumber of nickels to give: " + numNickels;
    }

}
