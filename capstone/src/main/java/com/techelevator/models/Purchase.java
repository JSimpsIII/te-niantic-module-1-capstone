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
        System.out.println("Your remaining balance is $" + getMoneyAvailable());
        return moneyAvailable;
    }

    public double change() {

        final double QUARTER = 0.25;
        final double DIME = 0.10;
        final double NICKEL = 0.05;
        final double PENNY = 0.01;

        double change = 0;
        double remainder = 0;

        boolean isRemainderZero = true;

        if (getMoneyAvailable() > QUARTER) {
            remainder = getMoneyAvailable() % QUARTER;
            if (remainder > DIME){
                remainder = getMoneyAvailable() % DIME;
            } if (remainder > NICKEL){
                remainder = getMoneyAvailable() % NICKEL;
            } if (remainder > PENNY){
                remainder = getMoneyAvailable() % PENNY;
            }


            System.out.println("You will receive " + change + " amount of quarters");
        }
    }

}
