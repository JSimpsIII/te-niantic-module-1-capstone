package com.techelevator.models;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;
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
        String newSelection = "";
        Product matchedItem = new Product();

        for(Product product : inventory.getProducts()) {
            if(item.equalsIgnoreCase(product.getRowId())) {
                matchedItem = product;
                price = matchedItem.getPrice();
                if (price > getMoneyAvailable()) {
                    String selection = UserInput.insufficientFundsSelection();
                    if (selection.equals("1")){
                        UserOutput.displayPurchase();
                    } else if (selection.equals("2")){
                        UserOutput.displayInventory(inventory);
                        newSelection = UserInput.getItemSelection();
                        makeSelection(inventory, newSelection);
                    }
                } else {
                    totalCost += price;
                    matchedItem.setQuantity(matchedItem.getQuantity() - 1);

                    if (matchedItem.getProductType().equals("Chip")) {
                        System.out.println("Crunch Crunch, Yum!");
                    } else if (matchedItem.getProductType().equals("Candy")) {
                        System.out.println("Munch Munch, Yum!");
                    } else if (matchedItem.getProductType().equals("Drink")) {
                        System.out.println("Glug Glug, Yum!");
                    } else if (matchedItem.getProductType().equals("Gum")) {
                        System.out.println("Chew Chew, Yum!");
                    }
                    System.out.println("You have purchased " + matchedItem.getName() + " for $" + matchedItem.getPrice() + ".");
                    Logger.logMessage(matchedItem.getName() + " " + matchedItem.getRowId() + " $" + matchedItem.getPrice()
                            + " $" + (getMoneyAvailable() - matchedItem.getPrice()) );
                }
                return totalCost;
            }
        }

            System.out.println("Item doesn't exist, please choose a different item: ");
            String stringReselection = UserInput.getItemSelection();
            makeSelection(inventory, stringReselection);

            return 0;
    }



    public double transaction() {
            moneyAvailable -= totalCost;
            totalCost = 0;
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

        moneyAvailable = 0;

        double displayChangeDue = (double) changeDue / 100;

        return "\nTotal amount of change to give: $" + displayChangeDue + "\nNumber of quarters to give: " + numQuarters +
                "\nNumber of dimes to give: " + numDimes + "\nNumber of nickels to give: " + numNickels;
    }

}
