package com.techelevator.application;

import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;
import com.techelevator.models.Inventory;
import com.techelevator.models.Purchase;
import com.techelevator.models.file_io.Logger;
import com.techelevator.view.Colors;

import java.awt.*;

public class VendingMachine
{
    //private class objects
    private Inventory inventory;
    private Purchase purchase;

    //constructor
    public VendingMachine(){
        inventory = new Inventory();
        inventory.loadInventory();
        purchase = new Purchase();
    }

    //method to begin application
    public void run()
    {
        // display home screen
        UserOutput.displayWelcomeScreen();

         while(true)
        {
            // display home screen and get user selection
            UserOutput.displayHomeScreenMenu();
            String option = UserInput.getMenuSelection();

            // "user selects 1) Display Vending Machine Items"
            if (option.equals("1")) {
                UserOutput.displayInventory(inventory.getProducts());

                //and prompt user to make purchase or go back to menu
                promptTransaction();

            // "user selects 2) Purchase Menu"
            } else if (option.equals("2")) {

                //call purchase method(below)
                purchase();

            // user selects (3) Exit
            } else if (option.equals("3")) {
                System.out.println();
                System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK + "╔════════════════════════════════════╗" + Colors.RESET);
                System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK + "║ Thank you for using Vend-Matic 800 ║" + Colors.RESET);
                System.out.println(Colors.YELLOW_BACKGROUND + Colors.BLACK + "╚════════════════════════════════════╝" + Colors.RESET);
                break; //application stops running

            // if user input not 1, 2, or 3
            } else {
                System.out.println(Colors.RED + "...................................." + Colors.RESET);
                System.out.println(Colors.RED + ": Invalid option, please try again :" + Colors.RESET);
                System.out.println(Colors.RED + ":..................................:" + Colors.RESET);
            }

        }

    }

    //prompt user to make purchase or go back to menu
    private void promptTransaction(){
        String option = UserInput.purchaseOrMainMenu();

        //if (Y) call purchase method(below)
        if (option.equalsIgnoreCase("Y")) {
            purchase();

        // if (menu)
        } else if (option.equalsIgnoreCase("Menu")) {
            // Go back to Menu
            return;

        //if user input not Yes or Menu
        } else {
            System.out.println(Colors.RED + "...................................." + Colors.RESET);
            System.out.println(Colors.RED + ": Invalid option, please try again :" + Colors.RESET);
            System.out.println(Colors.RED + ":..................................:" + Colors.RESET);
            promptTransaction();
        }

    }

    //user IO with purchase menu
    private void purchase(){

        // displays purchase menu, takes in money parameter
        // prompt user for selection
        UserOutput.displayPurchaseMenu(purchase.getMoneyAvailable());
        String option = UserInput.getMenuSelection();

        double money = 0;

        //use switch for strict equal of strings
        switch (option) {

            // user selects 1) Feed Money
            case "1":

                //allows customer to repeatedly feed money into the machine in whole dollar amounts.
                boolean continueLoop = true;
                while (continueLoop) {
                    money = UserInput.getPayment();
                    System.out.println();
                    purchase.feedMoney(money);
                    continueLoop = UserInput.continueOrNot();
                    Logger.logMessage("FEED MONEY $" + money + " $" + String.format("%.2f", purchase.getMoneyAvailable()));
                }
                break;

            // user selects (2) Select Product
            case "2":

                //make sure user has inserted money before selecting an item for purchase
                if (purchase.getMoneyAvailable() == 0){
                    System.out.println(Colors.RED + "................................................" + Colors.RESET);
                    System.out.println(Colors.RED + ": Please insert funds before choosing an item. :" + Colors.RESET);
                    System.out.println(Colors.RED + ":..............................................:" + Colors.RESET);

                // display inventory, get user input(item ID)
                // prints the item name, cost, and the money remaining
                } else {
                    UserOutput.displayInventory(inventory.getProducts());
                    System.out.println();
                    String item = UserInput.getItemSelection();
                    purchase.makeSelection(inventory, item);
                    purchase.transaction();
                }
                break;

            // user selects (3) Finish Transaction
            case "3":
                Logger.logMessage("GIVE CHANGE: $" + String.format("%.2f", purchase.getMoneyAvailable()) + " $0.00");
                System.out.println();
                System.out.println(Colors.GREEN + "........................" + Colors.RESET);
                System.out.println(Colors.GREEN + ": Transaction complete :" + Colors.RESET);
                System.out.println(Colors.GREEN + ":......................:" + Colors.RESET);
                System.out.println("..................................................");
                System.out.println(purchase.change()); //print out change in coins
                System.out.println("..................................................");
                return; //return out of function instead of calling purchase (end)

            // if user input not 1, 2, or 3
            default:
                System.out.println(Colors.RED + "....................................." + Colors.RESET);
                System.out.println(Colors.RED + ": Invalid option, please try again. :" + Colors.RESET);
                System.out.println(Colors.RED + ":...................................:" + Colors.RESET);
                break;
        }

        //call purchase at the end
        purchase();

    }

}
