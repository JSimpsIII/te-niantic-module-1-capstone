package com.techelevator.application;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;
import com.techelevator.models.Inventory;

public class VendingMachine
{
    private Inventory inventory;

    public VendingMachine(){
        inventory = new Inventory();
        inventory.loadInventory();
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
                viewItems();
                break;
            } else if (option.equals("2")) {
                purchase();
                break;
            } else if (option.equals("3")) {
                break;
            } else {
                // invalid
            }
        }
    }

    private void viewItems(){
        UserOutput.displayInventory(inventory);
        String option = UserInput.readyToPay();

        if (option.equals("Y")) {
            purchase();
        } else if (option.equals("N")) {
            viewItems();
        } else if (option.equals("Menu")) {
            UserOutput.displayHomeScreenMenu();
        } else {
            // invalid
        }
    }

    private void purchase(){
        UserOutput.displayPurchase();

        //System.out.println("Current Money Provided: " + payment);


    }
}
