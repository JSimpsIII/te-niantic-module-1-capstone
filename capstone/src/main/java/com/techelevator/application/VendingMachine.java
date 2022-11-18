package com.techelevator.application;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;
import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;

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

        if (option.equals("Y")) {
            purchase();
        } else if (option.equals("N")) {
            viewItems();
        } else if (option.equals("Menu")) {
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
                String wantsToContinue = UserInput.continueOrNot();
                if (wantsToContinue.equals("N")){
                    continueLoop = false;
                }
            }
            purchase();
        } else if (option.equals("2")) {
            UserOutput.displayInventory(inventory);
            System.out.println();
            String item = UserInput.getItemSelection();
            purchase.makeSelection(inventory, item);
            purchase.transaction();
            purchase();
        } else if (option.equals("3")) {
            System.out.println(); //change in coins
            System.out.println(purchase.change());;
        }

        //System.out.println("Current Money Provided: " + payment);


    }
}
