package com.techelevator.UI;

import java.util.Scanner;

public class UserInput {

    private static Scanner input = new Scanner(System.in);

    //prompts user to make selection in menu displayed
    public static String getMenuSelection(){

        System.out.println("Please make a selection: ");
        String selection = input.nextLine();

        return selection;
    }

    //prompts user for product ID from inventory list
    public static String getItemSelection() {

        System.out.println("Please enter item ID: ");
        String selection = input.nextLine();

        return selection;

    }

    //prompt user to insert correct bills for payment
    public static double getPayment() {
        System.out.println("Please Enter Whole Dollar Amount (1, 5, 10, or 20 dollar bills only): ");

            try {
                double amount = Double.parseDouble(input.nextLine());
                while (amount != 1 && amount != 5 && amount != 10 && amount != 20) {
                    System.out.println("This machine only accepts 1's, 5's, 10's, and 20's");
                    System.out.println();
                    System.out.println("Please Enter Whole Dollar Amount (1, 5, 10, or 20 dollar bills only): ");
                amount = Double.parseDouble(input.nextLine());
            }
                return amount;

            } catch(NumberFormatException e)
            {
                System.out.println("Invalid input, please try again");
            }
        return 0;
    }


    public static String purchaseOrMainMenu() {
        System.out.println();
        System.out.println("Are you ready to make a purchase(Y) or go back (Menu)?");
        String selection = input.nextLine();

        return selection;
    }

    //prompt user if they want to continue feeding money to machine
    public static boolean continueOrNot() {
        System.out.println("Do you want to add more money(Y/N)?");
        String selection = input.nextLine();
        boolean continueLoop = true;
        while (!selection.equalsIgnoreCase("Y") && !selection.equalsIgnoreCase("N")) {
            System.out.println("Invalid option, please try again");
            System.out.println();
            System.out.println("Do you want to add more money(Y/N)?");
            selection = input.nextLine();
        }
        if (selection.equalsIgnoreCase("N")) {
            continueLoop = false;
        }
        return continueLoop;
    }


    public static String insufficientFundsSelection(){
        System.out.println("Insufficient funds, press 1 to insert more money or press 2 to choose another item");
        String selection = input.nextLine();

        return selection;
    }
}
