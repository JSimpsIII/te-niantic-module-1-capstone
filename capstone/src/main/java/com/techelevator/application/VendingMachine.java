package com.techelevator.application;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;
import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;
import com.techelevator.models.file_io.Logger;

public class VendingMachine
{
    private Inventory inventory;
    private Purchase purchase;

    public VendingMachine(){
        inventory = new Inventory();
        inventory.loadInventory();
        purchase = new Purchase();
    }
    public void run()
    {
        // display home screen
        UserOutput.displayWelcomeScreen();

        while(true)
        {
            // get user selection
            UserOutput.displayHomeScreenMenu();
            String option = UserInput.getMenuSelection();

            if (option.equals("1")) {
                UserOutput.displayInventory(inventory);
                viewItems();
            } else if (option.equals("2")) {
                purchase();
            } else if (option.equals("3")) {
                break;
            } else {
                System.out.println("Invalid option, please try again");
            }
        }
    }

    private void viewItems(){
        String option = UserInput.readyToPay();

        if (option.equalsIgnoreCase("Y")) {
            purchase();
        } else if (option.equalsIgnoreCase("Menu")) {
            UserOutput.displayHomeScreenMenu();
        } else {
                System.out.println("Invalid option, please try again");
                viewItems();
            }
        }

    private void purchase(){
        UserOutput.displayPurchase();
        String option = UserInput.getMenuSelection();
        double money = 0;

        if (option.equals("1")) {
            boolean continueLoop = true;
            while (continueLoop) {
                money = UserInput.getPayment();
                purchase.feedMoney(money);
                continueLoop = UserInput.continueOrNot();
                Logger.logMessage("FEED MONEY $" + money + " $" + purchase.getMoneyAvailable());
            }
            purchase();

        } else if (option.equals("2")) {
            if (purchase.getMoneyAvailable() == 0){
                System.out.println("Please insert funds before choosing an item");
                purchase();
            } else {
                UserOutput.displayInventory(inventory);
                System.out.println();
                String item = UserInput.getItemSelection();
                purchase.makeSelection(inventory, item);
                purchase.transaction();
                purchase();
            }
        } else if (option.equals("3")) {
            System.out.println();
            Logger.logMessage("GIVE CHANGE: $" + purchase.getMoneyAvailable() + " $0.00");
            System.out.println(purchase.change());
        } else {
            System.out.println("Invalid option, please try again");
            purchase();
        }
    }
}
