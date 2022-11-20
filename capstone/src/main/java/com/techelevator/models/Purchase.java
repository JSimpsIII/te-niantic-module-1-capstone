package com.techelevator.models;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;
import com.techelevator.models.file_io.Logger;
import com.techelevator.models.products.Product;
import com.techelevator.view.Colors;

public class Purchase {

    //instance variables default to 0
    private double moneyAvailable = 0;
    private double totalCost = 0;

    // getters and setters
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    public double getMoneyAvailable() {
        return moneyAvailable;
    }

    //constructor
    public Purchase() {
        this.moneyAvailable = moneyAvailable;
    }

    //method to add input(money) to total money available
    public double feedMoney(double money) {
        moneyAvailable += money;
        return moneyAvailable;
    }

    // method for choosing item ID selection
    // takes in inventory list and item ID(user input)

    /**
     * method for choosing item ID selection
     * @param inventory
     * @param item
     * @return
     */
    public double makeSelection(Inventory inventory, String item) {
        double price = 0;
        String newSelection = "";
        Product matchedItem = new Product();

        //loop through inventory list
        for(Product product : inventory.getProducts()) {

            //if item(user input) matches product ID
            // assign product to matchedItem variable and get price
            if(item.equalsIgnoreCase(product.getRowId())) {
                matchedItem = product;
                price = matchedItem.getPrice();

                //if price exceeds user's available funds
                if (price > getMoneyAvailable()) {

                    //show insufficient funds message
                    String selection = UserInput.insufficientFundsSelection();

                    // return to purchase menu to feed money (1)
                    if (selection.equals("1")){

                    //display inventory and have user select another item
                    } else if (selection.equals("2")){
                        UserOutput.displayInventory(inventory.getProducts());
                        System.out.println();
                        newSelection = UserInput.getItemSelection();
                        makeSelection(inventory, newSelection);
                    }

                // if user has enough funds
                } else {
                    //if item is in stock, subtract that item purchased from stock
                    boolean successfullySetQuantity = matchedItem.setQuantity(matchedItem.getQuantity() - 1);

                    // if item not in stock, tell user
                    // display inventory and prompt user to choose another item
                    if (!successfullySetQuantity) {
                        UserOutput.displayInventory(inventory.getProducts());
                        System.out.println();
                        System.out.println(Colors.RED + "....................................................." + Colors.RESET);
                        System.out.println(Colors.RED + ": The item is SOLD OUT. Please choose another item. :" + Colors.RESET);
                        System.out.println(Colors.RED + ":...................................................:" + Colors.RESET);
                        System.out.println();
                        newSelection = UserInput.getItemSelection();
                        return makeSelection(inventory, newSelection);
                    }

                    // add price to total cost
                    totalCost += price;

                    //product type prints corresponding phrases
                    if (matchedItem.getProductType().equals("Chip")) {
                        System.out.println();
                        System.out.println(Colors.GREEN + "......................." + Colors.RESET);
                        System.out.println(Colors.GREEN + ": Crunch Crunch, Yum! :" + Colors.RESET);
                        System.out.println(Colors.GREEN + ":.....................:" + Colors.RESET);
                    } else if (matchedItem.getProductType().equals("Candy")) {
                        System.out.println();
                        System.out.println(Colors.GREEN + "....................." + Colors.RESET);
                        System.out.println(Colors.GREEN + ": Munch Munch, Yum! :" + Colors.RESET);
                        System.out.println(Colors.GREEN + ":...................:" + Colors.RESET);
                    } else if (matchedItem.getProductType().equals("Drink")) {
                        System.out.println();
                        System.out.println(Colors.GREEN + "..................." + Colors.RESET);
                        System.out.println(Colors.GREEN + ": Glug Glug, Yum! :" + Colors.RESET);
                        System.out.println(Colors.GREEN + ":.................:" + Colors.RESET);
                    } else if (matchedItem.getProductType().equals("Gum")) {
                        System.out.println();
                        System.out.println(Colors.GREEN + "..................." + Colors.RESET);
                        System.out.println(Colors.GREEN + ": Chew Chew, Yum! :" + Colors.RESET);
                        System.out.println(Colors.GREEN + ":.................:" + Colors.RESET);
                    }

                    // Dispensing an item prints the item name, cost, and the money remaining.
                    System.out.println();
                    System.out.println("..................................................");
                    System.out.println(" You have purchased " + matchedItem.getName() + " for $" + matchedItem.getPrice() + ". ");


                    Logger.logMessage(matchedItem.getName() + " " + matchedItem.getRowId() + " $" + String.format("%.2f",matchedItem.getPrice())
                            + " $" + String.format("%.2f", (getMoneyAvailable() - matchedItem.getPrice())) );
                }
                //return updated total cost
                return totalCost;
            }
        }
            // If the product code doesn't exist, the vending machine informs the
            // customer and returns them to the Purchase menu.
            System.out.println(Colors.RED + "......................................................." + Colors.RESET);
            System.out.println(Colors.RED + ": Item doesn't exist, please choose a different item. :" + Colors.RESET);
            System.out.println(Colors.RED + ":.....................................................:" + Colors.RESET);
            System.out.println();
            String stringReselection = UserInput.getItemSelection();
            makeSelection(inventory, stringReselection);

            return 0;
    }


    //method subtracts money spent from available funds and prints user balance
    public double transaction() {
            moneyAvailable -= totalCost;
            totalCost = 0;
        System.out.println(" Your remaining balance is $" + String.format("%.2f", getMoneyAvailable()) + ". ");
        System.out.println("..................................................");

        return moneyAvailable;

    }

    //method returns the customer's money using nickels, dimes, and quarters
    // (using the smallest amount of coins possible)
    public String change() {

        //define variables for coins
        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;

        //calculate the modulus in a hierarchy
        //multiply changeDue x100 since coin values are ints
        int changeDue = (int)(getMoneyAvailable()*100);
        int modQuarters = changeDue % QUARTER;
        int modDimes = modQuarters % DIME;
        int modNickels = modQuarters % NICKEL;

        //count the number of coins
        int numQuarters = (changeDue - modQuarters) / QUARTER;
        int numDimes = (modQuarters - modDimes) / DIME;
        int numNickels = (modDimes - modNickels) / NICKEL;

        //reset funds after giving change
        moneyAvailable = 0;

        //convert change amount back to double for printout
        double displayChangeDue = (double) changeDue / 100;

        return "\nTotal amount of change to give: $" + String.format("%.2f", displayChangeDue) + "\nNumber of quarters to give: " + numQuarters +
                "\nNumber of dimes to give: " + numDimes + "\nNumber of nickels to give: " + numNickels;
    }

}
